package edu.lethe.gameslib.repository;

import edu.lethe.gameslib.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    public Optional<AppUser> findByUsername(String username);
}
