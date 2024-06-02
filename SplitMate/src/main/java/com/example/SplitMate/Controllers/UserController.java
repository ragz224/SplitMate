package com.example.SplitMate.Controllers;

import com.example.SplitMate.DTOs.ExpenseDto.ExpenseRequestDto;
import com.example.SplitMate.DTOs.GroupDto.GroupRequestDto;
import com.example.SplitMate.DTOs.GroupDto.GroupResponseDto;
import com.example.SplitMate.Exceptions.UserNotFoundException;
import com.example.SplitMate.DTOs.UserRegisterRequestDto;
import com.example.SplitMate.DTOs.UserResponseDto;
import com.example.SplitMate.DTOs.UserloginRequestDto;
import com.example.SplitMate.Exceptions.EmailAlreadyRegisteredException;
import com.example.SplitMate.Models.Group;
import com.example.SplitMate.Repositories.GroupRepo;
import com.example.SplitMate.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;
    private final GroupRepo groupRepo;

    @Autowired
    public UserController(UserService userService,
                          GroupRepo groupRepo) {
        this.userService = userService;
        this.groupRepo = groupRepo;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> userRegistrationn(@RequestBody UserRegisterRequestDto userRegisterRequestDto) throws EmailAlreadyRegisteredException {
        return new ResponseEntity<>(userService.Register(userRegisterRequestDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public UserResponseDto userLogin(@RequestBody UserloginRequestDto userloginRequestDto) throws UserNotFoundException {
        System.out.println("yes");
        return userService.login(userloginRequestDto);

    }

    @PostMapping("/group")
    public ResponseEntity<GroupResponseDto> creatingGroup(@RequestBody GroupRequestDto groupRequestDto) {
        return new ResponseEntity<>(userService.creatGroup(groupRequestDto),HttpStatus.OK);
    }

    @PostMapping("/expense")
    public ResponseEntity<String> AddingExpense(@RequestBody ExpenseRequestDto expenseRequestDto) {
        return new ResponseEntity<>(userService.AddingExpense(expenseRequestDto),HttpStatus.OK);
    }

}

