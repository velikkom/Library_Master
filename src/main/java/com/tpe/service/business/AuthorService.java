package com.tpe.service.business;

import com.tpe.entity.business.Author;
import com.tpe.payload.request.business.AuthorRequest;
import com.tpe.payload.response.business.AuthorResponse;
import com.tpe.repository.business.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService
{
    private final AuthorRepository authorRepository;


    public AuthorResponse createAuthor(AuthorRequest authorRequest)
    {
        if (authorRepository.existsByName(authorRequest.getName())){
            throw new RuntimeException("Author with name " + authorRequest.getName() + " already exists");
        }
        Author author = new Author();
        author.setName(authorRequest.getName());

        Author savedAuthor = authorRepository.save(author);
        return mapToAuthorResponse(savedAuthor);
    }
    private AuthorResponse mapToAuthorResponse(Author author) {
        return AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }
}
