package com.mm.libraryrestapi.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotEmpty(message = "Username or email should not be null or empty")
    private String usernameOrEmail;

    @NotEmpty(message = "Password should not be null or empty")
    private String password;
}
