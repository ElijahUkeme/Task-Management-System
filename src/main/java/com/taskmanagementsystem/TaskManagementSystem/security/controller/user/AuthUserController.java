package com.taskmanagementsystem.TaskManagementSystem.security.controller.user;


import com.taskmanagementsystem.TaskManagementSystem.security.dto.AuthRequest;
import com.taskmanagementsystem.TaskManagementSystem.security.dto.RegisterDto;
import com.taskmanagementsystem.TaskManagementSystem.security.response.AuthenticationResponse;
import com.taskmanagementsystem.TaskManagementSystem.security.service.main.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthUserController {

    @Autowired
    private  UserService userService;


    @PostMapping("/api/auth/user/add")
    public ResponseEntity<AuthenticationResponse> addUser(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(userService.saveUser(registerDto));
    }

    @PostMapping("api/auth/user/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(userService.authenticate(authRequest));
    }
}
