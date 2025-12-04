package edu.lethe.gameslib.controller;

import edu.lethe.gameslib.config.TestConfig;
import edu.lethe.gameslib.model.Developer;
import edu.lethe.gameslib.model.Game;
import edu.lethe.gameslib.model.Publisher;
import edu.lethe.gameslib.repository.DeveloperRepository;
import edu.lethe.gameslib.repository.GameRepository;
import edu.lethe.gameslib.repository.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles(profiles = "test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = TestConfig.class)
public class GameIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    GameRepository repository;

    @Autowired
    DeveloperRepository developerRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @BeforeEach
    void setup(){
        developerRepository.deleteAll();
        publisherRepository.deleteAll();
        repository.deleteAll();

        Developer dev = new Developer("Remedy Entertainment");
        dev = developerRepository.save(dev);
        Publisher pub1 = new Publisher("Rockstar Games");
        pub1 = publisherRepository.save(pub1);
        Publisher pub2 = new Publisher("Electronic Arts");
        pub2 = publisherRepository.save(pub2);
        Publisher pub3 = new Publisher("Epic Games Publishing");
        pub3 = publisherRepository.save(pub3);
        Game game1 = new Game("Max Payne", dev, pub1);
        repository.save(game1);
        Game game2 = new Game("Alan Wake", dev, pub2);
        repository.save(game2);
        Game game3 = new Game("Alan Wake 2", dev, pub3);
        repository.save(game3);
    }

    @Test
    @DisplayName("getById should return status 200")
    void getByIdSuccessfulRetrieval() throws Exception{

        mockMvc.perform(get("/games/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Max Payne"));
    }

    @Test
    @DisplayName("getById should return status 200")
    void createGameShould() throws Exception{

        mockMvc.perform(post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "title": "Alan Wake's American Nightmare",
                                  "releaseDate": "22-09-2011",
                                  "developer":
                                  {
                                    "name": "Remedy Entertainment"
                                  },
                                  "publisher":
                                  {
                                    "name": "Electronic Arts"
                                  }
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("4"));
    }
}
