package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.payload.response.JWTAuthenticationResponse;
import com.mm.libraryrestapi.payload.dtos.LoginDto;
import com.mm.libraryrestapi.payload.dtos.RegisterDto;
import com.mm.libraryrestapi.services.AuthenticationService;
import com.mm.libraryrestapi.services.ConfirmationTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("confirm")
    public ResponseEntity<String> confirm(@RequestParam String token){
        return ResponseEntity.ok(confirmationTokenService.confirmToken(token));
    }
}
