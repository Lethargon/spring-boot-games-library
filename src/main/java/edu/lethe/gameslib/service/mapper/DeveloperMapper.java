package edu.lethe.gameslib.service.mapper;

import edu.lethe.gameslib.model.Developer;
import edu.lethe.gameslib.model.dto.DeveloperDTO;

public class DeveloperMapper {
    public static DeveloperDTO dtoFromEntity(Developer entity){
        DeveloperDTO dto = new DeveloperDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static Developer entityFromDto(DeveloperDTO dto){
        Developer entity = new Developer();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
