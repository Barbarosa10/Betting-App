package com.betuiasi.server.config;

import com.betuiasi.server.dto.UserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.OptionalInt;

public interface UserRepository extends MongoRepository<UserDTO, String> {
    Optional<UserDTO> findByUsernameAndPassword(String username, String password);
    Optional<UserDTO> findByUsername(String username);

}
