package saraya.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import saraya.entities.User;
import saraya.entities.enums.Position;
import saraya.repositories.UserRepository;
import saraya.requests.SignupRequest;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }

    public User create(SignupRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPosition(request.getPosition());
        user.setProfession(request.getProfession());
        return userRepository.save(user);
    }

    public User update(User user, Integer id) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public User disable(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User userToDisable = user.get();
            userToDisable.setIsEnabled(false);
            return userRepository.save(userToDisable);
        }
        return null;
    }

    public User promote(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User userToPromote = user.get();
            userToPromote.setPosition(Position.ADMIN);
            return userRepository.save(userToPromote);
        }
        return null;
    }

    public User changePosition(Integer id, Position position) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User userToChange = user.get();
            userToChange.setPosition(position);
            return userRepository.save(userToChange);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
