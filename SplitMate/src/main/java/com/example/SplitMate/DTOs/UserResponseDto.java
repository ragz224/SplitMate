package com.example.SplitMate.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String email;
    private String message;
    private String token;
}
