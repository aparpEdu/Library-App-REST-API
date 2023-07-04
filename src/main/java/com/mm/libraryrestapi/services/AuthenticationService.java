package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.LoginDto;
import com.mm.libraryrestapi.payload.RegisterDto;

public interface AuthenticationService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
