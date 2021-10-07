package com.personal_finances.controller;

import com.personal_finances.model.LoginUser;
import com.personal_finances.model.dto.LoginUserDTO;
import com.personal_finances.service.LoginUserService;

import com.personal_finances.utils.FormRoleToLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/api/login/user")
@RequiredArgsConstructor
public class LoginUserController {

    private final LoginUserService loginService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginUserDTO> saveLogin(@RequestBody LoginUser loginUser){
        return ResponseEntity.ok(loginService.save(loginUser));
    }

    @DeleteMapping
    public ResponseEntity<LoginUserDTO> deleteLogin(@RequestBody LoginUser loginUser){
        return ResponseEntity.ok(loginService.delete(loginUser.getUsername()));
    }

    @PostMapping(value = "/role", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginUserDTO> addRoleToLogin(@RequestBody FormRoleToLogin form){
        return ResponseEntity.ok(loginService.addRoleToLogin(form.getUsername(), form.getRoleName()));
    }

    @GetMapping(value = "/token/refresh", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        loginService.refreshToken(request, response);
        return ResponseEntity.ok().build();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LoginUserDTO>> findAllLogins(){
        return ResponseEntity.ok().body(loginService.findAllLongins());
    }

    @GetMapping(value = "/username", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginUserDTO> findByUsername(@RequestParam String username){
        return ResponseEntity.ok(loginService.findByUsername(username));
    }

}
