package fr.insa.gaming_chair_list.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data // génère Getters, Setters, ToString
@NoArgsConstructor // Génère le constructeur vide
@AllArgsConstructor // génère le constructeur avec tous les arguments
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;

    private String steamId64;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = "USER";
        this.createdAt = LocalDateTime.now();
    }
}