package com.project.core.service.impl;

import com.project.core.constant.ErrorMessage;
import com.project.core.exception.UserParametersException;
import com.project.db.repository.UserRepository;
import com.project.core.service.UserService;
import com.project.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> add(User user) {
        checkForEqualsPasswords(user.getPassword(), user.getVerifiedPassword());
        checkForContainsUsernameInDB(user.getUsername());
        checkForContainsEmailInDB(user.getEmail());
        return Optional.of(userRepository.save(user));
    }

    @Override
    public User getById(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getCurrentUser(){
        return (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }

    @Override
    public User getUserByEmailOrUsername(String emailOrUsername) {
        checkInputStringForNull(emailOrUsername);
        checkUserByEmailOrUsername(emailOrUsername);
        User user = userRepository.findByEmail(emailOrUsername);
        return Objects.isNull(user) ? userRepository.findByUsername(emailOrUsername) : user;
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    private void checkUserByEmailOrUsername(String emailOrUsername) {
        if (!userRepository.existsByUsername(emailOrUsername) && !userRepository.existsByEmail(emailOrUsername)) {
            throw new UserParametersException(ErrorMessage.CANNOT_FIND_USER + emailOrUsername);
        }
    }

    private void checkInputStringForNull(String inputString) {
        if (Objects.isNull(inputString)) {
            throw new UserParametersException(ErrorMessage.EMAIL_OR_USERNAME_CANNOT_BE_NULL);
        }
    }

    private void checkForEqualsPasswords(String password, String verifiedPassword) {
        if (!Objects.equals(password, verifiedPassword)) {
            throw new UserParametersException(ErrorMessage.NOT_EQUALS_PASSWORDS);
        }
    }

    private void checkForContainsUsernameInDB(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UserParametersException(ErrorMessage.THIS_USERNAME_IS_ALREADY_EXISTS_IN_DB);
        }
    }

    private void checkForContainsEmailInDB(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserParametersException(ErrorMessage.THIS_EMAIL_IS_ALREADY_EXISTS_IN_DB);
        }
    }
}
