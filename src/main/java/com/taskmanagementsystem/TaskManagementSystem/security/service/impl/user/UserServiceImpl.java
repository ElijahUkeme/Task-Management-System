package com.taskmanagementsystem.TaskManagementSystem.security.service.impl.user;

import com.taskmanagementsystem.TaskManagementSystem.security.config.JwtService;
import com.taskmanagementsystem.TaskManagementSystem.security.util.Role;
import com.taskmanagementsystem.TaskManagementSystem.security.dto.AuthRequest;
import com.taskmanagementsystem.TaskManagementSystem.security.dto.RegisterDto;
import com.taskmanagementsystem.TaskManagementSystem.security.entity.user.UserInfo;
import com.taskmanagementsystem.TaskManagementSystem.security.repository.user.UserInfoRepository;
import com.taskmanagementsystem.TaskManagementSystem.security.response.AuthenticationResponse;
import com.taskmanagementsystem.TaskManagementSystem.security.service.main.role.RoleService;
import com.taskmanagementsystem.TaskManagementSystem.security.service.main.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private   RoleService roleService;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private    AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse saveUser(RegisterDto registerDto) {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userInfo.setName(registerDto.getName());
        userInfo.setEmail(registerDto.getEmail());
        userInfo.setRole(Role.USER);
         userInfoRepository.save(userInfo);

         var jwtToken = jwtService.generateToken(userInfo);
         return AuthenticationResponse.builder()
                 .token(jwtToken)
                 .build();

    }

    @Override
    public AuthenticationResponse authenticate(AuthRequest authenticateDto) {


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticateDto.getEmail(),authenticateDto.getPassword()
        ));
        var user = userInfoRepository.findByEmail(authenticateDto.getEmail())
                .orElseThrow(()->new UsernameNotFoundException("User not found"));

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }


}
