package edu.lethe.gameslib.service;

import edu.lethe.gameslib.exception.ResourceNotFoundException;
import edu.lethe.gameslib.model.Developer;
import edu.lethe.gameslib.model.Game;
import edu.lethe.gameslib.model.Publisher;
import edu.lethe.gameslib.model.dto.DeveloperDTO;
import edu.lethe.gameslib.model.dto.GameRequestDTO;
import edu.lethe.gameslib.model.dto.GameResponseDTO;
import edu.lethe.gameslib.model.dto.PublisherDTO;
import edu.lethe.gameslib.repository.DeveloperRepository;
import edu.lethe.gameslib.repository.GameRepository;
import edu.lethe.gameslib.repository.PublisherRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    GameRepository repository;

    @Mock
    DeveloperRepository developerRepository;

    @Mock
    PublisherRepository publisherRepository;

    @Spy
    @InjectMocks
    GameService service;

    @Test
    @DisplayName("create should call save exactly once in repository")
    void create(){
        String devName = "Remedy Entertainment";
        DeveloperDTO devDto = new DeveloperDTO(devName);
        Developer dev = new Developer(devName);
        when(developerRepository.findByName(devName))
                .thenReturn(Optional.of(dev));

        String pubName = "Rockstar Games";
        PublisherDTO pubDto = new PublisherDTO(pubName);
        Publisher pub = new Publisher(pubName);
        when(publisherRepository.findByName((pubName)))
                .thenReturn(Optional.of(pub));

        String gameTitle = "Max Payne";
        Game entity = new Game("Max Payne", dev, pub);
        entity.setId(1L);
        GameRequestDTO dto = new GameRequestDTO();
        dto.setTitle(gameTitle);
        dto.setDeveloper(devDto);
        dto.setPublisher(pubDto);

        when(repository.save(any())).thenReturn(entity);

        service.create(dto);

        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("getById should return GameResponseDTO")
    void getById(){
        Developer dev = new Developer("Remedy Entertainment");
        Publisher pub = new Publisher("Rockstar Games");
        Game entity = new Game("Max Payne", dev, pub);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        GameResponseDTO result = service.getById(1L);

        assertNotNull(result);
        assertEquals("Max Payne", result.getTitle());
    }

    @Test
    @DisplayName("getById should throw ResourceNotFoundException")
    void getByIdThrowsCorrectException(){
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getById(1L));
    }
}
