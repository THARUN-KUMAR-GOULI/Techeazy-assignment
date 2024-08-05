package com.example.techeazy.Service;

import com.example.techeazy.Entity.Users;
import com.example.techeazy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticate(String username, String password){
        Users user = userRepository.findByUsername(username);
        if (user != null){
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    public String[] getUserRoles(String username){
        Users user = userRepository.findByUsername(username);
        if (user != null){
            return new String[]{user.getRole()};
        }
        return new String[]{};
    }

    public Users saveUser(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Users findByUsername(String username){
        return userRepository.findByUsername(username);
    }


}
