package fr.insa.gaming_chair_list.repository;

import fr.insa.gaming_chair_list.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    // Trouver tous les jeux d'un utilisateur spécifique via son ID
    List<Game> findByUserId(Long userId);
}