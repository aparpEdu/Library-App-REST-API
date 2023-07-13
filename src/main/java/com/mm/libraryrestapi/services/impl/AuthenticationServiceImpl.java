package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.entity.ConfirmationToken;
import com.mm.libraryrestapi.entity.Role;
import com.mm.libraryrestapi.entity.User;
import com.mm.libraryrestapi.exception.LibraryAPIException;
import com.mm.libraryrestapi.exception.ResourceNotFoundException;
import com.mm.libraryrestapi.payload.dtos.ChangePasswordDto;
import com.mm.libraryrestapi.payload.dtos.ForgotPasswordDto;
import com.mm.libraryrestapi.payload.dtos.LoginDto;
import com.mm.libraryrestapi.payload.dtos.RegisterDto;
import com.mm.libraryrestapi.repositories.RoleRepository;
import com.mm.libraryrestapi.repositories.UserRepository;
import com.mm.libraryrestapi.security.JwtTokenProvider;
import com.mm.libraryrestapi.services.AuthenticationService;
import com.mm.libraryrestapi.services.ConfirmationTokenService;
import com.mm.libraryrestapi.services.EmailService;
import com.mm.libraryrestapi.utils.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     UserRepository userRepository,
                                     RoleRepository roleRepository,
                                     PasswordEncoder passwordEncoder,
                                     JwtTokenProvider jwtTokenProvider,
                                     ConfirmationTokenService confirmationTokenService,
                                     EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.confirmationTokenService = confirmationTokenService;
        this.emailService = emailService;
    }

    @Override
    public String login(LoginDto loginDto) {
        User userToVerify = userRepository
                .getUserByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail())
                .orElseThrow(() -> new LibraryAPIException(HttpStatus.NOT_FOUND, ErrorMessages.NON_EXISTENT_USER));
        if(!userToVerify.isConfirmed()){
            throw new LibraryAPIException(HttpStatus.UNAUTHORIZED, ErrorMessages.NOT_CONFIRMED_EMAIL);
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw  new LibraryAPIException(HttpStatus.BAD_REQUEST, ErrorMessages.USERNAME_ALREADY_EXISTS);
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new LibraryAPIException(HttpStatus.BAD_REQUEST, ErrorMessages.EMAIL_ALREADY_EXISTS);
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setCity(registerDto.getCity());
        int userAge = LocalDate.now().getYear() - registerDto.getDateOfBirth().getYear();
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
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(token);
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        confirmationToken.setUser(user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        String confirmationLink = "http://localhost:8080/api/v1/auth/confirm?token=" + token;
        emailService.send(registerDto.getEmail(), emailService.buildEmail(registerDto.getName(), confirmationLink));
        return "User registered successfully! DEV TOKEN: "+ token;
    }

    @Override
    public String changePassword(ChangePasswordDto changePasswordDto, Long userId) {
        User userWithNewPassword = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userWithNewPassword.getUsername(), changePasswordDto.getOldPassword()));

        if(!changePasswordDto.getNewPassword().equals(changePasswordDto.getRepeatedNewPassword()))
            throw new LibraryAPIException(HttpStatus.BAD_REQUEST, ErrorMessages.NEW_PASSWORD_NO_MATCH);

        userWithNewPassword.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        userRepository.save(userWithNewPassword);
        emailService.send(userWithNewPassword.getEmail(), emailService.buildEmailChangePassword(userWithNewPassword.getName()));
        return "Password changed successfully";
    }

    @Override
    public String forgotPassword(ForgotPasswordDto forgotPasswordDto) {
        User userToResetPassword = userRepository
                .getUserByUsernameOrEmail(forgotPasswordDto.getUsernameOrEmail(), forgotPasswordDto.getUsernameOrEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "usernameOrEmail", forgotPasswordDto.getUsernameOrEmail()));
        String userEmail = userToResetPassword.getEmail();
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(token);
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        confirmationToken.setUser(userToResetPassword);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        String confirmationLink = "http://localhost:8080/api/v1/auth/reset?token=" + token;
        emailService.send(userEmail, emailService.buildEmailForgotPassword(userToResetPassword.getName(), confirmationLink));
        return "Email was sent with additional steps to reset your password! DEV TOKEN:" + token;
    }

    @Override
    public String resetPassword(ForgotPasswordDto forgotPasswordDto, String token) {
        User userToResetPassword =  confirmationTokenService.confirmResetToken(token).getUser();
        if(!forgotPasswordDto.getNewPassword().equals(forgotPasswordDto.getConfirmPassword())) {
            throw new LibraryAPIException(HttpStatus.BAD_REQUEST, ErrorMessages.NEW_PASSWORD_NO_MATCH);
        }
        userToResetPassword.setPassword(passwordEncoder.encode(forgotPasswordDto.getConfirmPassword()));
        userRepository.save(userToResetPassword);
        return "Password changed successfully";
    }
}
