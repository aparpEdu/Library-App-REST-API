package com.mm.libraryrestapi.payload.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordDto {
    private String usernameOrEmail;
    private String newPassword;
    private String confirmPassword;
}
