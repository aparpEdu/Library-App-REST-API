package com.mm.libraryrestapi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthenticationResponse {

    private String accessToken;
    private String tokenType = "Bearer";
}
