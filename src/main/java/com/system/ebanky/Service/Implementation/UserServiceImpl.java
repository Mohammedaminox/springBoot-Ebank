package com.system.ebanky.Service.Implementation;

import com.system.ebanky.DTO.UserDTO;
import com.system.ebanky.Entity.Enums.Role;
import com.system.ebanky.Entity.User;
import com.system.ebanky.Exception.NotFoundException;
import com.system.ebanky.Repository.UserRepository;
import com.system.ebanky.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> userMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        return userMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO cannot be null");
        }

        // Encode the password before saving the user
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(encodedPassword);  // Set the encoded password to the userDTO
        }

        // Map UserDTO to User entity
        User user = userMapper.map(userDTO, User.class);

        // Save to database
        User savedUser = userRepository.save(user);

        // Map back to UserDTO and return
        return userMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        userDTO.setId(userId);
        User updatedUser = userRepository.save(userMapper.map(userDTO, User.class));

        return userMapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        userRepository.deleteById(userId);
    }

    @Override
    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public boolean isUserAdmin(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
        return user.getRole() != null && user.getRole().equals(Role.ADMIN);
    }

    @Override
    public boolean isUserEmployee(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
        return user.getRole() != null && user.getRole().equals(Role.EMPLOYEE);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            return null; // Return null if no user is found
        }

        // Convert User entity to UserDTO
        UserDTO userDTO = userMapper.map(user.get(), UserDTO.class);
        return userDTO;
    }
}
