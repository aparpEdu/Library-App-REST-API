package com.mm.libraryrestapi.payload.response;

import com.mm.libraryrestapi.payload.dtos.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse extends GeneralResponse {
    private List<AuthorDto> content;
}
