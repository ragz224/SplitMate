package com.example.SplitMate.Secuity;



import com.example.SplitMate.Models.User;
import com.example.SplitMate.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("user not found"+ email);
        }
        User finalUser = user.get();
        return new org.springframework.security.core.userdetails.User(finalUser.getEmail(), finalUser.getPassword(), new ArrayList<>());
    }
}
