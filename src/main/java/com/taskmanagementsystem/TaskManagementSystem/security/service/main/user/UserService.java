package com.taskmanagementsystem.TaskManagementSystem.security.service.main.user;

import com.taskmanagementsystem.TaskManagementSystem.security.dto.AuthRequest;
import com.taskmanagementsystem.TaskManagementSystem.security.dto.RegisterDto;
import com.taskmanagementsystem.TaskManagementSystem.security.response.AuthenticationResponse;

public interface UserService {

    AuthenticationResponse saveUser(RegisterDto registerDto);
    public AuthenticationResponse authenticate(AuthRequest authenticateDto);
}
