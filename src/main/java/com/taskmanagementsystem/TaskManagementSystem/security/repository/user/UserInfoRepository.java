package com.taskmanagementsystem.TaskManagementSystem.security.repository.user;

import com.taskmanagementsystem.TaskManagementSystem.security.entity.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserInfoRepository extends JpaRepository <UserInfo, Integer> {
        Optional<UserInfo> findByEmail(String email);
        }