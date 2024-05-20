package com.taskmanagementsystem.TaskManagementSystem.security.controller.role;


import com.taskmanagementsystem.TaskManagementSystem.security.dto.RoleDto;
import com.taskmanagementsystem.TaskManagementSystem.security.service.main.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {


    @Autowired
    private RoleService roleService;


    @PostMapping("/role/add")
    public String addRole(@RequestBody RoleDto roleDto){
        return roleService.addRole(roleDto);
    }
}
