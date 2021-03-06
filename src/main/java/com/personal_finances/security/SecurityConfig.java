package com.personal_finances.security;

import com.personal_finances.utils.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.personal_finances.utils.RolesUsers.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Keys key;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), key);
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers(
                "/api/login/**",
                            "/api/login/token/refresh/**").permitAll();
        http.authorizeRequests().antMatchers(GET,"/api/**").hasAnyAuthority(ROLE_USER);
        http.authorizeRequests().antMatchers(GET, "/api/role/**").hasAnyAuthority(ROLE_ADMIN);
        http.authorizeRequests().antMatchers(POST, "/api/user/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/accounts/**").hasAnyAuthority(ROLE_USER);
        http.authorizeRequests().antMatchers(POST, "/api/categories/**").hasAnyAuthority(ROLE_USER);
        http.authorizeRequests().antMatchers(POST, "/api/expenses/**").hasAnyAuthority(ROLE_USER);
        http.authorizeRequests().antMatchers(POST, "/api/incomes/**").hasAnyAuthority(ROLE_USER);
        http.authorizeRequests().antMatchers(POST, "/api/role/**").hasAnyAuthority(ROLE_ADMIN);
        http.authorizeRequests().antMatchers(PUT, "/api/**").hasAnyAuthority(ROLE_USER);
        http.authorizeRequests().antMatchers(PUT, "/api/user/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(key), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
