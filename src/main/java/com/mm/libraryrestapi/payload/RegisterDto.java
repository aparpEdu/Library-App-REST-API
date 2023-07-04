package com.mm.libraryrestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    private String name;
    private String username;
    private String password;
    private String email;
    private long age;
    private String gender;
    private String address;
    private String city;
    private String country;
    private boolean euGdpr;
}
