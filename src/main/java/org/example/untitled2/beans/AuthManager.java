package org.example.untitled2.beans;
import org.example.untitled2.dbUtils.UserService;
import org.example.untitled2.utils.JWTManager;
import org.example.untitled2.utils.PasswordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthManager {

    private final UserService userService;

    @Autowired
    public AuthManager(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthRequest loginRequest) {
        System.out.println("Logging in user with username: " + loginRequest.getUsername());

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Optional<User> foundUser = userService.getUser(username);

        if (foundUser.isEmpty() || !PasswordManager.checkPassword(password, foundUser.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("access", "true");
        map.put("token", JWTManager.generateToken(foundUser.get().getId()));
        map.put("user_id", String.valueOf(foundUser.get().getId()));

        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody AuthRequest loginRequest) {
        System.out.println("Registering new user with username: " + loginRequest.getUsername());

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User newUser = new User(username, PasswordManager.hashPassword(password));

        userService.addUser(newUser);

        HashMap<String, String> map = new HashMap<>();
        map.put("access", "true");
        map.put("token", JWTManager.generateToken(newUser.getId()));
        map.put("user_id", String.valueOf(newUser.getId()));

        return ResponseEntity.ok().body(map);
    }
}
