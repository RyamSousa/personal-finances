package com.personal_finances.controller.impl;

import com.personal_finances.controller.LoginUserController;
import com.personal_finances.model.dto.LoginUserDTO;
import com.personal_finances.service.LoginUserService;

import com.personal_finances.utils.FormRoleToLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Component
@RequiredArgsConstructor
public class LoginUserControllerImpl implements LoginUserController {

    private final LoginUserService loginService;

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request);
        System.out.println(response);
        loginService.refreshToken(request, response);
    }

    @Override
    public ResponseEntity<List<LoginUserDTO>> findAllLogins(){
        return ResponseEntity.ok().body(loginService.findAllLongings());
    }

    @Override
    public ResponseEntity<LoginUserDTO> findByUsername(@RequestParam String username){
        return ResponseEntity.ok(loginService.findByUsername(username));
    }

}
