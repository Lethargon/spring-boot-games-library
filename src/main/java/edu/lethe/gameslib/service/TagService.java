package edu.lethe.gameslib.service;

import edu.lethe.gameslib.model.Tag;
import edu.lethe.gameslib.model.dto.TagDTO;
import edu.lethe.gameslib.repository.TagRepository;
import edu.lethe.gameslib.service.mapper.TagMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private TagRepository repository;

    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public TagDTO create(TagDTO dto){
        Tag entity = TagMapper.entityFromDto(dto);
        return TagMapper.dtoFromEntity(repository.save(entity));
    }

    public List<TagDTO> getAll(){
        return repository.findAll().stream().map(TagMapper::dtoFromEntity).toList();
    }
}
