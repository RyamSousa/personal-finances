package com.personal_finances.controller;

import com.personal_finances.model.Logins;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/login")
public interface LoginUserController {

    @GetMapping(value = "/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Logins>> findAllLogins();

    @GetMapping(value = "/username", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Logins> findByUsername(@RequestParam String username);
}
