package com.example.airbnb.services;

import com.example.airbnb.models.User;
import com.example.airbnb.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Пользователь с email '%s' не найден", email)));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPasswordHash(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList())
        );
    }
}