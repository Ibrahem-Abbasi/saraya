package saraya.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import saraya.entities.User;
import saraya.entities.enums.Position;
import saraya.requests.UserUpdateRequest;
import saraya.services.user.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService studentService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userService = studentService;
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        return userService.getById(id).map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<UserDetails> getUserByUsername(@PathVariable("username") String username) {
        UserDetails user = userService.loadUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserUpdateRequest request,
                                           @PathVariable("id") Integer id) {
        Optional<User> user = userService.getById(id);
        if (user.isPresent()) {
            User savedUser = user.get();
            savedUser.setName(request.getName());
            savedUser.setProfession(request.getProfession());
            savedUser.setPhone(request.getPhone());
            savedUser.setUsername(request.getUsername());
            savedUser.setPassword(passwordEncoder.encode(request.getPassword()));
            return ResponseEntity.ok(userService.update(savedUser, id));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/disable/{id}")
    public ResponseEntity<User> disableUser(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.disable(id));
    }

    @PatchMapping("/promote/{id}")
    public ResponseEntity<User> promoteUser(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.promote(id));
    }

    @PatchMapping("/change-position/{id}")
    public ResponseEntity<User> changePosition(@PathVariable("id") Integer id,
                                               @RequestBody Position position) {
        return ResponseEntity.ok(userService.changePosition(id, position));
    }
}
