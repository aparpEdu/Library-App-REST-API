package com.mm.libraryrestapi.payload.dtos;


import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordDto {
    private String usernameOrEmail;
    @Size(min = 8, message = "password should be at least 8 symbols long")
    private String newPassword;
    private String confirmPassword;
}
