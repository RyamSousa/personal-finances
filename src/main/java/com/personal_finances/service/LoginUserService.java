package com.personal_finances.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.mapper.LoginUserMapper;
import com.personal_finances.model.Logins;
import com.personal_finances.model.Role;
import com.personal_finances.model.dto.LoginUserDTO;
import com.personal_finances.repository.LoginRepository;
import com.personal_finances.repository.RoleRepository;
import com.personal_finances.utils.RolesUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.personal_finances.utils.MessagesExceptions.*;
import static com.personal_finances.utils.RolesUsers.ROLE_USER;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class LoginUserService implements UserDetailsService {

    private final LoginRepository loginRepository;
    private final LoginUserMapper loginUserMapper;

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       LoginUserDTO dto = this.findByUsername(username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Logins login = loginUserMapper.toLoginUser(dto);

        login.getRoles().forEach(
                role-> authorities.add(
                        new SimpleGrantedAuthority(role.getName())
                )
        );

        return new User(login.getUsername(), login.getPassword(), authorities);
    }

    public LoginUserDTO save(LoginUserDTO login){
        Optional<Logins> loginUser = loginRepository.findByUsername(login.getUsername());

        if (loginUser.isPresent()) {
            throw new BusinessException(USER_ALREADY_EXISTS);
        }

        login.setPassword(passwordEncoder.encode(login.getPassword()));
        Logins save = loginRepository.save(loginUserMapper.toLoginUser(login));
        this.addRoleToLogin(login.getUsername(), ROLE_USER);

        return loginUserMapper.toDto(save);
    }
    public LoginUserDTO update(LoginUserDTO login){
        Optional<Logins> loginUser = loginRepository.findByUsername(login.getUsername());

        if (loginUser.isEmpty()) {
            throw new BusinessException(USER_NOT_FOUND);
        }

        Logins save = loginRepository.save(loginUserMapper.toLoginUser(login));

        return loginUserMapper.toDto(save);
    }

    public LoginUserDTO delete(String username){
        LoginUserDTO login = this.findByUsername(username);

        loginRepository.delete(loginUserMapper.toLoginUser(login));

        return login;
    }

    public LoginUserDTO addRoleToLogin(String username, String roleName){
        LoginUserDTO login = this.findByUsername(username);
        Optional<Role> role = roleRepository.findByName(roleName);

        if (role.isEmpty()){
            throw new BusinessException(ROLE_NOT_FOUND);
        }

        login.getRoles().add(role.get());

        return login;
    }

    public LoginUserDTO findByUsername(String username){
        Optional<Logins> loginUser = loginRepository.findByUsername(username);

        if(loginUser.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return loginUserMapper.optionalToDto(loginUser);
    }

    public LoginUserDTO findByUsername(Logins loginUser){
        Optional<Logins> login = loginRepository.findByUsername(loginUser.getUsername());

        if(login.isPresent()){
            throw new BusinessException(USERNAME_ALREADY_EXISTS);
        }

        return loginUserMapper.toDto(loginUser);
    }

    public List<LoginUserDTO> findAllLongings(){
        return loginRepository.findAll()
                .stream().map(loginUserMapper::toDto).collect(Collectors.toList());
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Object> responseBody = new ArrayList<>();

        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);

                String username = decodedJWT.getSubject();
                LoginUserDTO dto = this.findByUsername(username);
                Logins loginUser = loginUserMapper.toLoginUser(dto);

                String access_token = JWT.create()
                        .withSubject(loginUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + (10 * 60 * 1000)))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", loginUser.getRoles()
                                .stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);

                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception){
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());

                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else {
            throw new RuntimeException(REFRESH_TOKEN_IS_MISSING);
        }
    }

    public String passwordEncode(String password){
        return passwordEncoder.encode(password);
    }
}
