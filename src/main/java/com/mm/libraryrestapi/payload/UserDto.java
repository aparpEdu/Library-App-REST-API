package com.mm.libraryrestapi.payload;

import com.mm.libraryrestapi.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotEmpty(message = "Name should not be null or empty")
    @Size(min = 2, message = "Name should have at least 6 characters")
    private String name;

    @NotEmpty(message = "Username should not be null or empty")
    @Size(min = 6, message = "Username should have at least 6 characters")
    private String username;

    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    @NotEmpty(message = "Password should not be null or empty")
    @Size(min = 8, max = 20, message = "Password should be between 8 and 20 characters")
    private String password;

    @NotEmpty(message = "Age should not be null or empty")
    private long age;

    @NotEmpty(message = "Gender should not be null or empty")
    private String gender;

    @NotEmpty(message = "Address should not be null or empty")
    @Size(min = 6, message = "Address should have at least 6 characters")
    private String address;

    @NotEmpty(message = "City should not be null or empty")
    @Size(min = 4, message = "City should have at least 6 characters")
    private String city;

    @NotNull(message = "Birthday should not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Country should not be null or empty")
    @Size(min = 2, message = "Country should have at least 6 characters")
    private String country;

    @NotEmpty(message = "Role should not be null or empty")
    private Set<Role> roles;
}
