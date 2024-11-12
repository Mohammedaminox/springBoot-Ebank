package com.banque.ebankify.service;

import com.banque.ebankify.dto.request.UserRequestDTO;
import com.banque.ebankify.dto.response.UserResponseDTO;
import com.banque.ebankify.entity.User;
import com.banque.ebankify.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword())); // Encrypt password
        user.setRole(User.Role.USER); // Default role
        user.setEmail(userRequestDTO.getEmail());
        userRepository.save(user);
        return new UserResponseDTO(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDTO(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        userRepository.save(user);
        return new UserResponseDTO(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
