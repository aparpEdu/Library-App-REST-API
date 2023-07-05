package com.mm.libraryrestapi.controller;

import com.mm.libraryrestapi.payload.JWTAuthenticationResponse;
import com.mm.libraryrestapi.payload.LoginDto;
import com.mm.libraryrestapi.payload.RegisterDto;
import com.mm.libraryrestapi.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService  authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthenticationResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JWTAuthenticationResponse response = new JWTAuthenticationResponse();
        response.setAccessToken(token);
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
