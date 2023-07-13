package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.payload.dtos.ChangePasswordDto;
import com.mm.libraryrestapi.payload.dtos.ForgotPasswordDto;
import com.mm.libraryrestapi.payload.dtos.LoginDto;
import com.mm.libraryrestapi.payload.dtos.RegisterDto;

public interface AuthenticationService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

    String changePassword(ChangePasswordDto changePasswordDto, Long userId);

    String forgotPassword(ForgotPasswordDto forgotPasswordDto);

    String resetPassword(ForgotPasswordDto forgotPasswordDto, String token);
}
