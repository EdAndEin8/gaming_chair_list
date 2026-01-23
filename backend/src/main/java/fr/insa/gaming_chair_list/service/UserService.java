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
    private final PasswordEncoder passwordEncoder; // 1. Déclaration de la variable manquante

    // 2. Injection via le constructeur
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Méthode pour inscrire un utilisateur
    public User registerUser(User user) {
        // Vérifications
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé !");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Ce pseudo est déjà pris !");
        }

        // Hachage du mot de passe (utilise l'instance injectée)
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Sauvegarder
        return userRepository.save(user);
    }

    // Méthode de Login (Indispensable pour l'étape JWT)
    public User login(String email, String rawPassword) {
        // 1. Chercher l'utilisateur par email
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // 2. Vérifier si le mot de passe correspond au hash en base
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return user;
            }
        }
        // Si l'utilisateur n'existe pas ou mot de passe faux
        throw new RuntimeException("Email ou mot de passe incorrect");
    }

    // Méthodes utilitaires
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}