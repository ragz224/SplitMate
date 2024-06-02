package com.example.SplitMate.DTOs.GroupDto;

import com.example.SplitMate.Models.Role;
import com.example.SplitMate.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class GroupRequestDto {
    private String name;
    private String description;
    private String strtDate;
    private String endDate;
    private List<User> userList;

}
