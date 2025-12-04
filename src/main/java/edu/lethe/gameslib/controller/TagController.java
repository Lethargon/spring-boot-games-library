package edu.lethe.gameslib.controller;

import edu.lethe.gameslib.model.dto.TagDTO;
import edu.lethe.gameslib.service.TagService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tags")
@RestController
public class TagController {
    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    @GetMapping
    public List<TagDTO> getAll(){
        return service.getAll();
    }

    @PostMapping
    public TagDTO create(@Valid @RequestBody TagDTO dto){
        return service.create(dto);
    }
}
