    package com.system.ebanky.Controller;
    import com.system.ebanky.Controller.vm.User.Request.CreateUserRequest;
    import com.system.ebanky.DTO.UserDTO;
    import com.system.ebanky.Entity.Enums.Role;
    import com.system.ebanky.Service.UserService;
    import com.system.ebanky.Util.JwtUtil;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.annotation.Secured;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/users")
    public class UserController {

        @Autowired
        private UserService userService;

        private  JwtUtil jwtUtil;

        @GetMapping
        public ResponseEntity<List<UserDTO>> getAllUsers() {
            List<UserDTO> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        }

        @GetMapping("/{userId}")
        public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
            UserDTO user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        }

        @PostMapping
        public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) {
            // Validate input
            if (createUserRequest.getUserDTO() == null) {
                return ResponseEntity.badRequest().body("UserDTO cannot be null");
            }

            UserDTO userDTO = createUserRequest.getUserDTO();
            String role = userDTO.getRole().toString();

            // Assign default role if not provided
            if (role == null || role.isEmpty()) {
                userDTO.setRole(Role.USER);
            } else if ("ADMIN".equals(role)) {
                userDTO.setRole(Role.ADMIN);
            }

            // Create user and return response
            UserDTO createdUser = userService.createUser(userDTO);
            return ResponseEntity.ok(createdUser);
        }



        @PutMapping("/{userId}")
        public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
            UserDTO updatedUser = userService.updateUser(userId, userDTO);
            return ResponseEntity.ok(updatedUser);
        }

        @DeleteMapping("/{userId}")
        public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        }

    }