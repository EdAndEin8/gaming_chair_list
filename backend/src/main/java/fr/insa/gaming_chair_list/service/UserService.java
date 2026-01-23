package fr.insa.gaming_chair_list.service;

import fr.insa.gaming_chair_list.entity.User;
import fr.insa.gaming_chair_list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Méthode pour inscrire un utilisateur
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé !");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Ce pseudo est déjà pris !");
        }

        // 2. Hasher le mot de passe
        // user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 3. Sauvegarder
        return userRepository.save(user);
    }

    // Méthode pour trouver un utilisateur
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Juste pour tester : lister tous les users
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}