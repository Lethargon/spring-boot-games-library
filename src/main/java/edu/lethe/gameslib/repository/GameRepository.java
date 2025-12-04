package edu.lethe.gameslib.repository;

import edu.lethe.gameslib.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("games")
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByTitleIgnoreCase(String title);
    List<Game> findByTitleContainsIgnoreCase(String title);
}
