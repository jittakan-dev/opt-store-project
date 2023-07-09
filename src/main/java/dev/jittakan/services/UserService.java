package dev.jittakan.services;

import dev.jittakan.models.entities.User;
import jakarta.ejb.Local;

import java.util.Optional;

@Local
public interface UserService {
    Optional<User> login(String username, String password);
}
