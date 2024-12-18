package com.system.ebanky.Controller;

import com.system.ebanky.DTO.LoginRequest; // Import the LoginRequest DTO
import com.system.ebanky.DTO.UserDTO;
import com.system.ebanky.Service.UserService;
import com.system.ebanky.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Authenticate user
        UserDTO user = userService.getUserByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        // Generate token
        String token = jwtUtil.generateToken(user.getId(), user.getRole().toString());
        return ResponseEntity.ok(token);
    }
}