package org.example.untitled2.dbUtils;

import org.example.untitled2.beans.User;
import org.example.untitled2.utils.PasswordManager;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> getUser(@NonNull String username) {
        final var user = userRepository.findByUsername(username);
        return Optional.ofNullable(user);
    }

    @Override
    public boolean addUser(@NonNull User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            log.info("User with username {} already exists", user.getUsername());
            return false;
        }
        user.setPassword(PasswordManager.hashPassword(user.getPassword()));
        userRepository.save(user);
        log.info("User with username {} was added", user.getUsername());
        return true;
    }
}
