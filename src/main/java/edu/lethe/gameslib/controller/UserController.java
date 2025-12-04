package edu.lethe.gameslib.controller;

import edu.lethe.gameslib.model.dto.GameReviewDTO;
import edu.lethe.gameslib.model.dto.UserRequestDTO;
import edu.lethe.gameslib.model.dto.UserResponseDTO;
import edu.lethe.gameslib.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("prod")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    public List<UserResponseDTO> getAll(){
        return service.getAll();
    }

    @PostMapping
    public UserResponseDTO create(@Valid @RequestBody UserRequestDTO dto){
        return service.create(dto);
    }

    @GetMapping("/reviews")
    public List<GameReviewDTO> getActiveUserReviews(){
        return service.getActiveUserReviews();
    }
}
