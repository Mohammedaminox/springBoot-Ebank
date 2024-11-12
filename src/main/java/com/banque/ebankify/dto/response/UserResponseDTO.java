package com.banque.ebankify.dto.response;

import com.banque.ebankify.entity.User;
import com.banque.ebankify.entity.User.Role;

public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private Role role;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }
}
