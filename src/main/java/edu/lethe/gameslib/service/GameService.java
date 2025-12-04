package edu.lethe.gameslib.service;

import edu.lethe.gameslib.exception.ResourceNotFoundException;
import edu.lethe.gameslib.model.*;
import edu.lethe.gameslib.model.dto.*;
import edu.lethe.gameslib.repository.*;
import edu.lethe.gameslib.service.mapper.GameMapper;
import edu.lethe.gameslib.service.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    @Autowired
    private GameRepository repository;
    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public List<GameResponseDTO> getAll(){
        return repository.findAll().stream().map(GameMapper::responseFromEntity).toList();
    }

    public GameResponseDTO getById(Long id){
        Game entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find game with ID = " + id));
        return GameMapper.responseFromEntity(entity);
    }

    public GameResponseDTO create(GameRequestDTO dto){
        Game entity = GameMapper.entityFromRequest(dto);
        if (dto.getDeveloper() == null || dto.getPublisher() == null)
            throw new RuntimeException("Posting a new game requires publisher and developer.");

        Developer existingDeveloper = findDeveloperFromDto(dto.getDeveloper());
        entity.setDeveloper(existingDeveloper);

        Publisher existingPublisher = findPublisherFromDto(dto.getPublisher());
        entity.setPublisher(existingPublisher);

        if(dto.getTags() != null)
            entity.setTags(dto.getTags().stream().map(this::findTagFromDto).collect(Collectors.toSet()));

        return GameMapper.responseFromEntity(repository.save(entity));
    }

    public GameReviewDTO review(Long id, ReviewRequestDTO reviewRequestDTO){
        Game game = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find game with ID = " + id));
        AppUser user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new ResourceNotFoundException("Active user does not exist."));
        Review review = new Review();
        review.setGame(game);
        review.setUser(user);
        review.setContent(reviewRequestDTO.getContent());
        review.setScore(reviewRequestDTO.getScore());
        return ReviewMapper.dtoFromEntity(reviewRepository.save(review));
    }

    private Developer findDeveloperFromDto(DeveloperDTO dto){
        return developerRepository.findById(dto.getId() == null ? -1 : dto.getId()).orElse(
                developerRepository.findByName(dto.getName() == null ? "" : dto.getName()).orElseThrow(
                        () -> new ResourceNotFoundException("Couldn't find a developer with the given information.")));
    }

    private Publisher findPublisherFromDto(PublisherDTO dto){
        return publisherRepository.findById(dto.getId() == null ? -1 : dto.getId()).orElse(
                publisherRepository.findByName(dto.getName() == null ? "" : dto.getName()).orElseThrow(
                        () -> new ResourceNotFoundException("Couldn't find a developer with the given information.")));
    }

    private Tag findTagFromDto(TagDTO dto){
        return tagRepository.findById(dto.getId() == null ? -1 : dto.getId()).orElse(
                tagRepository.findByName(dto.getName() == null ? "" : dto.getName()).orElseThrow(
                        () -> new ResourceNotFoundException("Couldn't find any tag with the given information.")));
    }
}
