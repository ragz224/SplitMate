package com.example.SplitMate.Services;


import com.example.SplitMate.DTOs.ExpenseDto.ExpenseRequestDto;
import com.example.SplitMate.DTOs.GroupDto.GroupRequestDto;
import com.example.SplitMate.DTOs.GroupDto.GroupResponseDto;
import com.example.SplitMate.Exceptions.UserNotFoundException;
import com.example.SplitMate.DTOs.UserRegisterRequestDto;
import com.example.SplitMate.DTOs.UserResponseDto;
import com.example.SplitMate.DTOs.UserloginRequestDto;
import com.example.SplitMate.Exceptions.EmailAlreadyRegisteredException;
import com.example.SplitMate.Models.*;
import com.example.SplitMate.Repositories.GroupRepo;
import com.example.SplitMate.Repositories.SessionRepo;
import com.example.SplitMate.Repositories.UserRepo;
import com.example.SplitMate.Secuity.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepo userRepo;
    GroupRepo groupRepo;
    private SessionRepo sessionRepo;

    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;


    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

//    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder , GroupRepo groupRepo,
                       JwtUtil jwtUtil, AuthenticationManager authenticationManager,UserDetailsService userDetailsService,
                       SessionRepo sessionRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.groupRepo = groupRepo;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.sessionRepo = sessionRepo;


    }


    public UserResponseDto Register(UserRegisterRequestDto userRegisterRequestDto) throws EmailAlreadyRegisteredException {

      Optional<User> optionalUser = userRepo.findByEmail(userRegisterRequestDto.getEmail());
      if(!optionalUser.isEmpty()) {
          throw new EmailAlreadyRegisteredException("user this email"+ userRegisterRequestDto.getEmail() + " already registered");
      }
      User user = new User();
      user.setEmail(userRegisterRequestDto.getEmail());
      user.setName(userRegisterRequestDto.getName());
      user.setPassword(passwordEncoder.encode(userRegisterRequestDto.getPassword()));
      User SavedUser = userRepo.save(user);
      UserResponseDto userResponseDto = new UserResponseDto();
      userResponseDto.setEmail(SavedUser.getEmail());
      userResponseDto.setMessage(SavedUser.getEmail() + " has been registered successfully");

        return userResponseDto;
    }

    public UserResponseDto login(UserloginRequestDto userloginRequestDto) throws UserNotFoundException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userloginRequestDto.getEmail(), userloginRequestDto.getPassword())
            );
       } catch (Exception e) {
          throw new UserNotFoundException("Incorrect email or password");
      }
        Optional<User> optionalUser = userRepo.findByEmail(userloginRequestDto.getEmail());
        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("user with Email"+ userloginRequestDto.getEmail()+" Not Found");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userloginRequestDto.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);
        Session session = new Session();
        session.setUser(optionalUser.get());
        session.setSessionStatus(SessionStatus.Active);
        session.setToken(jwt);

        sessionRepo.save(session);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(userloginRequestDto.getEmail());
        userResponseDto.setToken(jwt);
        userResponseDto.setMessage("user login was sucessfull");
        return userResponseDto;
    }

    public GroupResponseDto creatGroup(GroupRequestDto groupRequestDto) {

        Group group = new Group();
        group.setName(groupRequestDto.getName());
        group.setStrtDate(groupRequestDto.getStrtDate());
        group.setEndDate(groupRequestDto.getEndDate());

        List<User> userList = new ArrayList<>();
       for(User userr:groupRequestDto.getUserList()) {
           User user = new User();
           user.setName(userr.getName());
           user.setEmail(userr.getEmail());
           user.setPassword(passwordEncoder.encode(userr.getPassword()));
           userRepo.save(user);
           userList.add(user);
       }
      group.setUsers(userList);
        Group  savedGroup = groupRepo.save(group);
        GroupResponseDto groupResponseDto = new GroupResponseDto();
        groupResponseDto.setName(savedGroup.getName());
         groupResponseDto.setMessage("group created sucessfully ! Happing addings");

        return groupResponseDto;

    }

public String AddingExpense(ExpenseRequestDto expenseRequestDto) {

    Optional<Group> Groupname = groupRepo.findByName(expenseRequestDto.getGroup().getName());
    if(Groupname.isEmpty()) {
        throw new RuntimeException();
    }

    Expense expense = new Expense();
      expense.setAmount(expenseRequestDto.getExpense().getAmount());
      expense.setGroup(expenseRequestDto.getGroup());
      expense.setDate(expenseRequestDto.getDate());
      expense.setCurrency(expenseRequestDto.getCurrenccy());
      String info = "expense added ";

      return info;
}


}
