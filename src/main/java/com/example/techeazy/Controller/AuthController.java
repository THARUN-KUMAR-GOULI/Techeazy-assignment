package com.example.techeazy.Controller;

import com.example.techeazy.Component.JWTUtil;
import com.example.techeazy.Dto.LoginRequestDto;
import com.example.techeazy.Dto.LoginResponseDto;
import com.example.techeazy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        if(userService.authenticate(username, password)){
            String[] roles = userService.getUserRoles(username);
            String token = jwtUtil.generateToken(username, roles);
            return new LoginResponseDto(token);
        } else {
            throw new RuntimeException("Invalid Credentials !");
        }
    }
}
