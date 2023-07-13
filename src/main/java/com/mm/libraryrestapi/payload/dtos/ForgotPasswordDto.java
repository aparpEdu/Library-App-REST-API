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
    @NotEmpty(message = "Password should not be left empty")
    @Size(min = 8, message = "password should be at least 8 symbols long")
    private String newPassword;
    @NotNull
    private String confirmPassword;
}
