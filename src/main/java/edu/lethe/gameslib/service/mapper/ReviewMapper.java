package edu.lethe.gameslib.service.mapper;

import edu.lethe.gameslib.model.Review;
import edu.lethe.gameslib.model.dto.GameReviewDTO;

public class ReviewMapper {
    public static GameReviewDTO dtoFromEntity(Review entity){
        GameReviewDTO dto = new GameReviewDTO();
        dto.setGameId(entity.getGame().getId());
        dto.setTitle(entity.getGame().getTitle());
        dto.setUserId(entity.getUser().getId());
        dto.setUsername(entity.getUser().getUsername());
        dto.setReviewId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setScore(entity.getScore());
        return dto;
    }
}
