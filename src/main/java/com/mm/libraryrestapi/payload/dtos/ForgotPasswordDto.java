package com.mm.libraryrestapi.payload.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordDto {
    private String usernameOrEmail;
    private String newPassword;
    private String confirmPassword;
}
