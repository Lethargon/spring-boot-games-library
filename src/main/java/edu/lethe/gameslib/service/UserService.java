package edu.lethe.gameslib.service;

import edu.lethe.gameslib.exception.InvalidTokenException;
import edu.lethe.gameslib.exception.ResourceNotFoundException;
import edu.lethe.gameslib.model.AppUser;
import edu.lethe.gameslib.model.Role;
import edu.lethe.gameslib.model.dto.*;
import edu.lethe.gameslib.repository.RoleRepository;
import edu.lethe.gameslib.repository.UserRepository;
import edu.lethe.gameslib.service.mapper.ReviewMapper;
import edu.lethe.gameslib.service.mapper.UserMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Profile("prod")
@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    public JwtDTO getTokensFromLoginDto(LoginRequestDTO dto){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        String role = auth.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");

        String accessToken = jwtUtil.generateToken(dto.getUsername(), role);
        String refreshToken = jwtUtil.generateRefreshToken(dto.getUsername());

        AppUser user = repository.findByUsername(dto.getUsername()).orElseThrow(() -> new ResourceNotFoundException("Couldn't find user with given username."));
        user.setRefreshToken(refreshToken);
        repository.save(user);

        return new JwtDTO(accessToken, refreshToken);
    }

    public JwtDTO getTokensFromRefresh(String refreshToken){
        Claims claims = jwtUtil.extractClaims(refreshToken);

        String username = claims.getSubject();

        AppUser user = repository.findByUsername(username)
                .orElseThrow(() -> new InvalidTokenException("Token doesn't have a corresponding user."));

        if(!refreshToken.equals(user.getRefreshToken()))
            throw new InvalidTokenException("Invalid refresh token.");

        String role = user.getRoles().stream().findFirst().map(Role::getName)
                .orElseThrow(() -> new ResourceNotFoundException("User is missing roles."));

        String accessToken = jwtUtil.generateToken(username, role);

        return new JwtDTO(accessToken, refreshToken);
    }

    public List<UserResponseDTO> getAll(){
        return repository.findAll().stream().map(UserMapper::dtoFromEntity).toList();
    }

    public UserResponseDTO create(UserRequestDTO dto){
        AppUser entity = UserMapper.entityFromDto(dto);
        entity.setRoles(dto.getRoles().stream().map(this::getRoleByName).collect(Collectors.toSet()));
        return UserMapper.dtoFromEntity(repository.save(entity));
    }

    public List<GameReviewDTO> getActiveUserReviews(){
        AppUser activeUser = repository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new ResourceNotFoundException("Active user does not exist."));
        return activeUser.getReviews().stream().map(ReviewMapper::dtoFromEntity).toList();
    }

    private Role getRoleByName(String name){
        return roleRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Couldn't find role with name = " + name));
    }
}
