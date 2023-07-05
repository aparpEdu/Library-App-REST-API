package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.entity.Role;
import com.mm.libraryrestapi.entity.User;
import com.mm.libraryrestapi.exception.LibraryAPIException;
import com.mm.libraryrestapi.payload.LoginDto;
import com.mm.libraryrestapi.payload.RegisterDto;
import com.mm.libraryrestapi.repositories.RoleRepository;
import com.mm.libraryrestapi.repositories.UserRepository;
import com.mm.libraryrestapi.security.JwtTokenProvider;
import com.mm.libraryrestapi.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw  new LibraryAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new LibraryAPIException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setCity(registerDto.getCity());
        int userAge = LocalDate.now().getYear() - user.getDateOfBirth().getYear();
        user.setAge(userAge);
        user.setAddress(registerDto.getAddress());
        user.setCountry(registerDto.getCountry());
        user.setGender(registerDto.getGender());
        user.setEmail(registerDto.getEmail());
        user.setDateOfBirth(registerDto.getDateOfBirth());

        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        Role role = new Role();
        if (userRole.isPresent()) {
            role = userRole.get();
        }
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);
        return "User registered successfully!";
    }
}
