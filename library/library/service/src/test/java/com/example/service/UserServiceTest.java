package com.example.service;

import com.example.core.entity.Users;
import com.example.core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void shouldGetAllUsers() {
        List<Users> users = List.of(
                new Users("User1", "user1@example.com", "password1"),
                new Users("User2", "user2@example.com", "password2")
        );
        when(userRepository.findAll()).thenReturn(users);

        List<Users> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("User1", result.get(0).getUsername());
        assertEquals("user1@example.com", result.get(0).getEmail());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldGetUserById() {
        Users user = new Users("User", "user@example.com", "password");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Users result = userService.getUserById(1L);

        assertEquals("User", result.getUsername());
        assertEquals("user@example.com", result.getEmail());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void shouldCreateUser() {
        Users user = new Users("NewUser", "newuser@example.com", "newpassword");
        when(userRepository.save(any(Users.class))).thenReturn(user);

        Users result = userService.createUser(user);

        assertEquals("NewUser", result.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldUpdateUser() {
        Users existingUser = new Users("OldUser", "olduser@example.com", "oldpassword");
        Users updatedUser = new Users("UpdatedUser", "updateduser@example.com", "updatedpassword");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(updatedUser);

        Users result = userService.updateUser(1L, updatedUser);

        assertEquals("UpdatedUser", result.getUsername());
        assertEquals("updateduser@example.com", result.getEmail());
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void shouldDeleteUser() {
        Users user = new Users("User", "user@example.com", "password");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        verify(userRepository, times(1)).delete(user);
    }
}
