package com.mm.libraryrestapi.payload;

import com.mm.libraryrestapi.entity.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
@Data
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private long age;
    private String gender;
    private String address;
    private String city;
    private String country;
    private Set<Role> roles;
}
