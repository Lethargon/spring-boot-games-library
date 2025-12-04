package edu.lethe.gameslib.controller;

import edu.lethe.gameslib.model.dto.GameRequestDTO;
import edu.lethe.gameslib.model.dto.GameResponseDTO;
import edu.lethe.gameslib.model.dto.GameReviewDTO;
import edu.lethe.gameslib.model.dto.ReviewRequestDTO;
import edu.lethe.gameslib.service.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/games")
@RestController
public class GameController {
    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping
    public List<GameResponseDTO> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GameResponseDTO getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<GameResponseDTO> create(@Valid @RequestBody GameRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PostMapping("/reviews")
    public GameReviewDTO review(@RequestParam Long id, @Valid @RequestBody ReviewRequestDTO dto){
        return service.review(id, dto);
    }
}
