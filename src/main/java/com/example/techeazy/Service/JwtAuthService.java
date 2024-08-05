package com.example.techeazy.Service;

import com.example.techeazy.Component.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthService {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    public UserDetails getUserDetails(String jwt){
        String username = jwtUtil.extractUsername(jwt);
        return userDetailsService.loadUserByUsername(username);
    }

    public boolean validateToken(String jwt, UserDetails userDetails){
        return jwtUtil.validateToken(jwt, userDetails.getUsername());
    }
}
