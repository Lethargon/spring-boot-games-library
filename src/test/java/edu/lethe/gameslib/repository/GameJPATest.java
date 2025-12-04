package edu.lethe.gameslib.repository;

import edu.lethe.gameslib.model.Developer;
import edu.lethe.gameslib.model.Game;
import edu.lethe.gameslib.model.Publisher;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class GameJPATest {

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
    }

    @Test
    @DisplayName("findByTitle should return correct entity")
    void testFindByTitle(){
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

        List<Game> exactMatch = repository.findByTitleIgnoreCase("Alan Wake");
        assertThat(exactMatch).hasSize(1);
        assertThat(exactMatch).extracting(Game::getTitle).contains("Alan Wake");
    }

    @Test
    @DisplayName("findByTitleContains should return correct entities")
    void testFindByTitleContains(){
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

        List<Game> exactMatch = repository.findByTitleContainsIgnoreCase("Alan Wake");
        assertThat(exactMatch).hasSize(2);
        assertThat(exactMatch).extracting(Game::getTitle).containsExactlyInAnyOrder("Alan Wake", "Alan Wake 2");
    }

    @Test
    @DisplayName("title field does not allow null values")
    void testConstraints(){
        Game game = new Game();
        assertThrows(ConstraintViolationException.class, () -> repository.save(game));
    }
}
