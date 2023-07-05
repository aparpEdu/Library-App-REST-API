package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.entity.Author;
import com.mm.libraryrestapi.exception.ResourceNotFoundException;
import com.mm.libraryrestapi.payload.AuthorDto;
import com.mm.libraryrestapi.payload.AuthorResponse;
import com.mm.libraryrestapi.repositories.AuthorRepository;
import com.mm.libraryrestapi.services.AuthorService;
import com.mm.libraryrestapi.utils.CustomMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CustomMapper mapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, CustomMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = mapToEntity(authorDto);
        Author newAuthor = authorRepository.save(author);
        return mapToDTO(newAuthor);
    }

    @Override
    public AuthorResponse getAllAuthors(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Author> allAuthors = authorRepository.findAll(pageable);

        List<Author> authorList = allAuthors.getContent();

        List<AuthorDto> authors = authorList.stream().map(this::mapToDTO).collect(Collectors.toList());
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setAuthorDtoList(authors);
        authorResponse.setPageNo(allAuthors.getNumber());
        authorResponse.setPageSize(allAuthors.getSize());
        authorResponse.setTotalElements(allAuthors.getTotalElements());
        authorResponse.setTotalPages(allAuthors.getTotalPages());
        authorResponse.setLast(allAuthors.isLast());
        return authorResponse;
    }

    @Override
    public AuthorDto getAuthorById(long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        return mapToDTO(author);
    }

    @Override
    public AuthorDto updateAuthorById(AuthorDto authorDto, long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setCountry(author.getCountry());
        author.setBirthDate(author.getBirthDate());
        author.setDeathDate(author.getDeathDate());

        Author updatedAuthor = authorRepository.save(author);
        return mapToDTO(updatedAuthor);
    }

    @Override
    public void deleteAuthorById(long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        authorRepository.delete(author);
    }

    private Author mapToEntity(AuthorDto authorDto) {
        return mapper.map(authorDto, Author.class);
    }

    private AuthorDto mapToDTO(Author author) {
        return mapper.map(author, AuthorDto.class);
    }
}
