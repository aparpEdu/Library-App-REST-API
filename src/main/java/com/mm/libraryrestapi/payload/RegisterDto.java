package com.mm.libraryrestapi.payload;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @NotEmpty(message = "Name should not be null or empty")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @NotEmpty(message = "Username should not be null or empty")
    @Size(min = 6, message = "Username should have at least 6 characters")
    private String username;

    @NotEmpty(message = "Password should not be null or empty")
    @Size(min = 8, max = 20, message = "Password should be between 8 and 20 characters")
    private String password;

    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    private long age;

    @NotNull(message = "Birthday should not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Gender should not be null or empty")
    private String gender;

    @NotEmpty(message = "Address should not be null or empty")
    @Size(min = 6, message = "Address should have at least 6 characters")
    private String address;

    @NotEmpty(message = "City should not be null or empty")
    @Size(min = 4, message = "City should have at least 4 characters")
    private String city;

    @NotEmpty(message = "Country should not be null or empty")
    @Size(min = 2, message = "Country should have at least 4 characters")
    private String country;

    @NotNull(message = "GDPR should not be null")
    @AssertTrue(message = "GDPR Should be accepted!")
    private Boolean euGdpr;
}
