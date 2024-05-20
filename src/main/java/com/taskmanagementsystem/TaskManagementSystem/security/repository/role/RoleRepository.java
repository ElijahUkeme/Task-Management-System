package com.taskmanagementsystem.TaskManagementSystem.security.repository.role;

import com.taskmanagementsystem.TaskManagementSystem.security.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
