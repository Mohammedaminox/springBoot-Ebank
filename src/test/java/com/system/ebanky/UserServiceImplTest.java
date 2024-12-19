package com.system.ebanky;

import com.system.ebanky.DTO.UserDTO;
import com.system.ebanky.Entity.Enums.Role;
import com.system.ebanky.Entity.User;
import com.system.ebanky.Exception.NotFoundException;
import com.system.ebanky.Repository.UserRepository;
import com.system.ebanky.Service.Implementation.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password123")
                .age(30)
                .creditScore(700)
                .role(Role.USER)
                .monthly_income(5000.00)
                .build();

        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("John Doe");
        userDTO.setEmail("john.doe@example.com");
        userDTO.setPassword("password123");
        userDTO.setAge(30);
        userDTO.setCreditScore(700);
        userDTO.setRole(Role.USER);
        userDTO.setMonthly_income(5000.00);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUserDTOs() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        List<UserDTO> users = userService.getAllUsers();

        assertEquals(1, users.size());
        assertEquals(userDTO, users.get(0));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById_ShouldReturnUserDTO_WhenUserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        UserDTO result = userService.getUserById(1L);

        assertEquals(userDTO, result);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUserById_ShouldThrowNotFoundException_WhenUserDoesNotExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getUserById(1L));
        verify(userRepository, times(1)).findById(1L);
    }


    @Test
    void updateUser_ShouldReturnUpdatedUserDTO() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(modelMapper.map(userDTO, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        UserDTO result = userService.updateUser(1L, userDTO);

        assertEquals(userDTO, result);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteUser_ShouldInvokeRepositoryDelete_WhenUserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_ShouldThrowNotFoundException_WhenUserDoesNotExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.deleteUser(1L));
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void isUserAdmin_ShouldReturnTrue_WhenUserIsAdmin() {
        user.setRole(Role.ADMIN);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        boolean result = userService.isUserAdmin(1L);

        assertTrue(result);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void isUserAdmin_ShouldReturnFalse_WhenUserIsNotAdmin() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        boolean result = userService.isUserAdmin(1L);

        assertFalse(result);
        verify(userRepository, times(1)).findById(1L);
    }
}
