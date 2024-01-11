package org.example.untitled2.dbUtils;

import org.example.untitled2.beans.User;
import lombok.NonNull;

import java.util.Optional;

public interface UserService {
    Optional<User> getUser(@NonNull String username);

    boolean addUser(@NonNull User user);
}
