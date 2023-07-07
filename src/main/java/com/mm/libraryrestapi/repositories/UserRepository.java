package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
@RepositoryRestResource
@Tag(name = "User Repository")
public interface UserRepository extends JpaRepository<User, Long> {

    @Operation(
            summary = "Get User By Username Or Email",
            description = "Search User By Username Or Email is used to get a user from the database"
    )
    Optional<User> getUserByUsernameOrEmail(String username, String email);

    @Operation(
            summary = "Get User By Username",
            description = "Search User By Username is used to see if a user exists in the database"
    )
    Boolean existsByUsername(String username);

    @Operation(
            summary = "Get User By Email",
            description = "Search User By Email is used to see if a user exists in the database"
    )
    Boolean existsByEmail(String email);
}
