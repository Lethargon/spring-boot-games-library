package edu.lethe.gameslib.controller;

import edu.lethe.gameslib.model.dto.JwtDTO;
import edu.lethe.gameslib.model.dto.LoginRequestDTO;
import edu.lethe.gameslib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

@Profile("prod")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService service;

    @PostMapping
    public JwtDTO login(@RequestBody LoginRequestDTO dto){
        return service.getTokensFromLoginDto(dto);
    }

    @PostMapping("/refresh")
    public JwtDTO refresh(@RequestParam String refreshToken){
        return service.getTokensFromRefresh(refreshToken);
    }
}
