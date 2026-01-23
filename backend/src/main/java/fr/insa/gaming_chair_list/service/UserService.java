package fr.insa.gaming_chair_list.service;

import fr.insa.gaming_chair_list.entity.User;
import fr.insa.gaming_chair_list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; //Déclaration de la variable manquante

    // Injection via le constructeur
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Méthode pour inscrire un utilisateur
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé !");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Ce pseudo est déjà pris !");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        return userRepository.save(user);
    }

    // Méthode de Login
    public User login(String email, String rawPassword) {

        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return user;
            }
        }

        throw new RuntimeException("Email ou mot de passe incorrect");
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}