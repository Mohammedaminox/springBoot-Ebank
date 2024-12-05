package com.system.ebanky.Controller.vm.User.Request;

import com.system.ebanky.DTO.UserDTO;

public class CreateUserRequest {
    private UserDTO userDTO;
    private String role;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
