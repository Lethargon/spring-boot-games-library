package edu.lethe.gameslib.service.mapper;

import edu.lethe.gameslib.model.Game;
import edu.lethe.gameslib.model.Tag;
import edu.lethe.gameslib.model.dto.GameRequestDTO;
import edu.lethe.gameslib.model.dto.GameResponseDTO;

public class GameMapper {
    public static GameResponseDTO responseFromEntity(Game entity){
        GameResponseDTO dto = new GameResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setReleaseDate(entity.getReleaseDate());
        dto.setDeveloper(DeveloperMapper.dtoFromEntity(entity.getDeveloper()));
        dto.setPublisher(PublisherMapper.dtoFromEntity(entity.getPublisher()));
        if (entity.getTags() != null){
            dto.setTags(entity.getTags().stream().map(Tag::getName).toList());
            dto.setAgeRestriction(entity.getTags().stream().map(Tag::getAgeRestriction).reduce(Math::max).orElse(0));
        } else {
            dto.setAgeRestriction(0);
        }

        return dto;
    }

    public static Game entityFromRequest(GameRequestDTO dto){
        Game entity = new Game();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setReleaseDate(dto.getReleaseDate());
        return entity;
    }
}
