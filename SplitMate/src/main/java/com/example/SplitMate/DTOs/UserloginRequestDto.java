package com.example.SplitMate.DTOs;

import com.example.SplitMate.Models.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserloginRequestDto {
    private String email;
    private String password;
    private Role role;
}
