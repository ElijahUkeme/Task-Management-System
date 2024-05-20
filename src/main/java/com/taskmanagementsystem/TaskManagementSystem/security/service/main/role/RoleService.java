package com.taskmanagementsystem.TaskManagementSystem.security.service.main.role;

import com.taskmanagementsystem.TaskManagementSystem.security.dto.RoleDto;
import com.taskmanagementsystem.TaskManagementSystem.security.entity.role.Role;

public interface RoleService {

    Role findByName(String name);
    String addRole(RoleDto roleDto);
}
