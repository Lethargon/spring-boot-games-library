package edu.lethe.gameslib.controller;

import edu.lethe.gameslib.model.dto.DeveloperDTO;
import edu.lethe.gameslib.service.DeveloperService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/developers")
@RestController
public class DeveloperController {
    private final DeveloperService service;

    public DeveloperController(DeveloperService service) {
        this.service = service;
    }

    @GetMapping
    public List<DeveloperDTO> getAll(){
        return service.getAll();
    }

    @PostMapping
    public DeveloperDTO create(@Valid @RequestBody DeveloperDTO dto){
        return service.create(dto);
    }
}
