package com.project.core.service;

import com.project.db.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> add(User user);

    User getById(long id);

    User getUserByEmailOrUsername(String emailOrUsername);

    void delete(User user);

    User getCurrentUser();
}
