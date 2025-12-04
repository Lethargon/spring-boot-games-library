package edu.lethe.gameslib.controller;

import edu.lethe.gameslib.model.dto.PublisherDTO;
import edu.lethe.gameslib.service.PublisherService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/publishers")
@RestController
public class PublisherController {
    private final PublisherService service;

    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @GetMapping
    public List<PublisherDTO> getAll(){
        return service.getAll();
    }

    @PostMapping
    public PublisherDTO create(@Valid @RequestBody PublisherDTO dto){
        return service.create(dto);
    }
}
