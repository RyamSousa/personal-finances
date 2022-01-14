package com.personal_finances.controller.impl;

import com.personal_finances.controller.LoginUserController;
import com.personal_finances.model.Logins;
import com.personal_finances.service.LoginUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    public ResponseEntity<List<Logins>> findAllLogins(){
        return ResponseEntity.ok().body(loginService.findAllLongings());
    }

    @Override
    public ResponseEntity<Logins> findByUsername(@RequestParam String username){
        return ResponseEntity.ok(loginService.findByUsername(username));
    }

}
