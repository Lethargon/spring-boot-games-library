package edu.lethe.gameslib.service;

import edu.lethe.gameslib.model.Developer;
import edu.lethe.gameslib.model.dto.DeveloperDTO;
import edu.lethe.gameslib.repository.DeveloperRepository;
import edu.lethe.gameslib.service.mapper.DeveloperMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {
    private DeveloperRepository repository;

    public DeveloperService(DeveloperRepository repository) {
        this.repository = repository;
    }

    public DeveloperDTO create(DeveloperDTO dto){
        Developer entity = DeveloperMapper.entityFromDto(dto);
        return DeveloperMapper.dtoFromEntity(repository.save(entity));
    }

    public List<DeveloperDTO> getAll(){
        return repository.findAll().stream().map(DeveloperMapper::dtoFromEntity).toList();
    }
}
