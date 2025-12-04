package edu.lethe.gameslib.service.mapper;

import edu.lethe.gameslib.model.Publisher;
import edu.lethe.gameslib.model.dto.PublisherDTO;

public class PublisherMapper {
    public static PublisherDTO dtoFromEntity(Publisher entity){
        PublisherDTO dto = new PublisherDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static Publisher entityFromDto(PublisherDTO dto){
        Publisher entity = new Publisher();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
