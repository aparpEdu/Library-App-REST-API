package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.payload.dtos.ChangePasswordDto;
import com.mm.libraryrestapi.payload.dtos.ForgotPasswordDto;
import com.mm.libraryrestapi.payload.response.JWTAuthenticationResponse;
import com.mm.libraryrestapi.payload.dtos.LoginDto;
import com.mm.libraryrestapi.payload.dtos.RegisterDto;
import com.mm.libraryrestapi.services.AuthenticationService;
import com.mm.libraryrestapi.services.ConfirmationTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@Tag(name = "Login and Register REST APIs for Authentication Resource")
public class AuthenticationController {

    private final AuthenticationService  authService;
    private final ConfirmationTokenService confirmationTokenService;

    public AuthenticationController(AuthenticationService authService, ConfirmationTokenService confirmationTokenService) {
        this.authService = authService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Operation(
            summary = "Login User REST API",
            description = "Login User REST API is used to login a particular user into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthenticationResponse> login(@Valid @RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JWTAuthenticationResponse response = new JWTAuthenticationResponse();
        response.setAccessToken(token);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Register User REST API",
            description = "Register User REST API is used to register a new user into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Confirm User's Email REST API",
            description = "Confirm User's Email REST API is used to confirm a new user's email by confirmation token"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @GetMapping("confirm")
    public ResponseEntity<String> confirm(@RequestParam String token){
        return ResponseEntity.ok(confirmationTokenService.confirmToken(token));
    }

    @Operation(
            summary = "Change User Password REST API",
            description = "Change User Password API is used to change the password of an existing user in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping (value ="/{userId}/changePassword")
    public ResponseEntity<String> changePassword(
            @RequestBody @Valid ChangePasswordDto changePasswordDto,
            @PathVariable Long userId)
    {
        String response = authService.changePassword(changePasswordDto,userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Forgot Password Request REST API",
            description = "Forgot Password Request REST API is used to send reset token to user's email "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PostMapping("forgot")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto){
        return ResponseEntity.ok(authService.forgotPassword(forgotPasswordDto));
    }
    @Operation(
            summary = "Reset User Password REST API",
            description = "Reset User Password REST API is used to reset user's password"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PutMapping("reset")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @Valid @RequestBody ForgotPasswordDto forgotPasswordDto){
       return ResponseEntity.ok(authService.resetPassword(forgotPasswordDto, token));
    }
}
