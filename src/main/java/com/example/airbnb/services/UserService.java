package com.example.airbnb.services;

import com.example.airbnb.dto.RegistrationUserDTO;
import com.example.airbnb.models.Role;
import com.example.airbnb.models.User;
import com.example.airbnb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(RegistrationUserDTO registrationUserDTO) {
        User user = new User();
        user.setEmail(registrationUserDTO.getEmail());
        user.setFullName(registrationUserDTO.getFullName());
        user.setPasswordHash(passwordEncoder.encode(registrationUserDTO.getPassword()));
        // По умолчанию все новые пользователи - гости
        user.setRoles(Set.of(Role.ROLE_GUEST));
        return userRepository.save(user);
    }
}