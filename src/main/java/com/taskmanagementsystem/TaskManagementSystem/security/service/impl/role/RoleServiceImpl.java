package com.taskmanagementsystem.TaskManagementSystem.security.service.impl.role;

import com.taskmanagementsystem.TaskManagementSystem.security.dto.RoleDto;
import com.taskmanagementsystem.TaskManagementSystem.security.entity.role.Role;
import com.taskmanagementsystem.TaskManagementSystem.security.repository.role.RoleRepository;
import com.taskmanagementsystem.TaskManagementSystem.security.service.main.role.RoleService;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public String addRole(RoleDto roleDto) {
        Role role = Role.builder()
                .name(roleDto.getName())
                .build();
        repository.save(role);
        return "Role Added Successfully";
    }
}
