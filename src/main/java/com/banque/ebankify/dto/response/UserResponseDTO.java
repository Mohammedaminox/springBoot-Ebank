package com.banque.ebankify.dto.response;

// UserResponseDTO : Utilisé pour les réponses contenant les informations d'un utilisateur sans le mot de passe
import com.banque.ebankify.entity.User.Role;

public class UserResponseDTO {
    private Long id;
    private String username;
    private Role role;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
