package com.example.SplitMate.DTOs;

import com.example.SplitMate.Models.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestDto {
    private String name;
    private String email;
    private String password;
    private Role role;
}
