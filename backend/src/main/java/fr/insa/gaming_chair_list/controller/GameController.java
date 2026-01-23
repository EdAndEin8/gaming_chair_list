package fr.insa.gaming_chair_list.controller;

import fr.insa.gaming_chair_list.entity.Game;
import fr.insa.gaming_chair_list.entity.User;
import fr.insa.gaming_chair_list.repository.GameRepository;
import fr.insa.gaming_chair_list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    @Autowired
    public GameController(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    // 1. Récupérer MES jeux (uniquement ceux de l'utilisateur connecté)
    @GetMapping
    public List<Game> getMyGames() {
        User currentUser = getCurrentUser();
        return gameRepository.findByUserId(currentUser.getId());
    }

    // 2. Ajouter un jeu à MA liste
    @PostMapping
    public Game addGame(@RequestBody Game game) {
        User currentUser = getCurrentUser();

        // On lie le jeu à l'utilisateur connecté
        game.setUser(currentUser);

        // On définit un statut par défaut si vide
        if (game.getStatus() == null) {
            game.setStatus("A FAIRE");
        }

        return gameRepository.save(game);
    }

    // 3. Supprimer un jeu
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Long id) {
        // On vérifie que le jeu appartient bien à l'utilisateur avant de supprimer !
        // (Pour l'instant on fait simple, on supprime direct)
        gameRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // --- Méthode utilitaire pour savoir QUI est connecté ---
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Le pseudo stocké dans le JWT

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }
}