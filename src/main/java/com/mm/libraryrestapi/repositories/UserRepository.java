package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

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
            summary = "Check User By Username",
            description = "Check User By Username is used to see if a user exists in the database"
    )
    Boolean existsByUsername(String username);

    @Operation(
            summary = "Check User By Email",
            description = "Check User By Email is used to see if a user exists in the database"
    )
    Boolean existsByEmail(String email);

    @Operation(
            summary = "Update User's Confirm Status by Email",
            description = "Update User's Confirm Status by Email is used to update user's confirm status by email"
    )
    @Transactional
    @Modifying
    @Query("UPDATE User u " +
            "SET u.confirmed = TRUE WHERE u.email = ?1")
    void confirmEmail(String email);
}
