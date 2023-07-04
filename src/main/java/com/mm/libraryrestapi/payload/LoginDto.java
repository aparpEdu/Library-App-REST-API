package com.mm.libraryrestapi.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotEmpty(message = "Username or email should not be null or empty")
    private String usernameOrEmail;

    @NotEmpty(message = "Password should not be null or empty")
    private String password;
}
