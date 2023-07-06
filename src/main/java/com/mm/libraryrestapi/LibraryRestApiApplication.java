package com.mm.libraryrestapi;

import com.mm.libraryrestapi.repositories.RoleRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Spring Boot Library App REST APIs",
        description = "Spring Boot Library App REST APIs Documentation",
        version = "v1.0",
        contact = @Contact(
                name = "Ruben, Alexandr, Daniel",
                email = "triple@gmail.com"
        )
),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot Library App Documentation",
                url = "https://github.com/aparpEdu/Library-App-REST-API"
        )
)
public class LibraryRestApiApplication  {
    public static void main(String[] args) {
        SpringApplication.run(LibraryRestApiApplication.class, args);
    }

//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }

    @Autowired
    private RoleRepository roleRepository;

//    @Override
//    public void run(String... args) throws Exception {
//        Role adminRole = new Role();
//        adminRole.setName("ROLE_ADMIN");
//        roleRepository.save(adminRole);
//
//        Role userRole = new Role();
//        userRole.setName("ROLE_USER");
//        roleRepository.save(userRole);
//    }
}
