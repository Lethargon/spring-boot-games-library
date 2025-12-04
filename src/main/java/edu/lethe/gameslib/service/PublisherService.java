package edu.lethe.gameslib.service;

import edu.lethe.gameslib.model.Publisher;
import edu.lethe.gameslib.model.dto.PublisherDTO;
import edu.lethe.gameslib.repository.PublisherRepository;
import edu.lethe.gameslib.service.mapper.PublisherMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    private PublisherRepository repository;

    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    public PublisherDTO create(PublisherDTO dto){
        Publisher entity = PublisherMapper.entityFromDto(dto);
        return PublisherMapper.dtoFromEntity(repository.save(entity));
    }

    public List<PublisherDTO> getAll(){
        return repository.findAll().stream().map(PublisherMapper::dtoFromEntity).toList();
    }
}
