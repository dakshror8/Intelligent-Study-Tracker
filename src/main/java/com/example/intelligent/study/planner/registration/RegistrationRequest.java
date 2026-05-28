package com.example.intelligent.study.planner.registration;

import com.example.intelligent.study.planner.app_user.UserRole;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private String name;
    private String email;
    private String password;
    private UserRole userRole;
}
