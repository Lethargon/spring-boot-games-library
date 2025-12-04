package edu.lethe.gameslib.service.mapper;

import edu.lethe.gameslib.model.Tag;
import edu.lethe.gameslib.model.dto.TagDTO;

public class TagMapper {
    public static TagDTO dtoFromEntity(Tag entity){
        TagDTO dto = new TagDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAgeRestriction(entity.getAgeRestriction());
        return dto;
    }

    public static Tag entityFromDto(TagDTO dto){
        Tag entity = new Tag();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAgeRestriction(dto.getAgeRestriction() == null ? 0 : dto.getAgeRestriction());
        return entity;
    }
}
