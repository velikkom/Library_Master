package com.tpe.controller.business;

import com.tpe.payload.request.business.AuthorRequest;
import com.tpe.payload.response.business.AuthorResponse;
import com.tpe.service.business.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController
{
    private final AuthorService authorService;

    //http://localhost:8080/authors/save
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody AuthorRequest authorRequest)
    {
      AuthorResponse authorResponse =  authorService.createAuthor(authorRequest);
      return new  ResponseEntity<>(authorResponse, HttpStatus.CREATED);
    }


}
