package com.banque.ebankify.dto.request;

// UserRequestDTO : Utilisé pour les requêtes de création et de mise à jour d'un utilisateur
public class UserRequestDTO {
    private String username;
    private String password;
    private Long roleId;

    // Getters et Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
}

