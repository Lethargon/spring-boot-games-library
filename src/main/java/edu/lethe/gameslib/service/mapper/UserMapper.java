package edu.lethe.gameslib.service.mapper;

import edu.lethe.gameslib.model.AppUser;
import edu.lethe.gameslib.model.Role;
import edu.lethe.gameslib.model.dto.UserRequestDTO;
import edu.lethe.gameslib.model.dto.UserResponseDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserMapper {
    public static AppUser entityFromDto(UserRequestDTO dto){
        AppUser entity = new AppUser();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        return entity;
    }

    public static UserResponseDTO dtoFromEntity(AppUser entity){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setRoles(entity.getRoles().stream().map(Role::getName).toList());
        return dto;
    }
}
