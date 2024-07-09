package com.tpe.controller.business;

import com.tpe.entity.business.Publisher;
import com.tpe.payload.request.business.PublisherRequest;
import com.tpe.payload.response.business.PublisherResponse;
import com.tpe.service.business.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publishers")
public class PublisherController
{
    private final PublisherService publisherService;

    //http://localhost:8080/authors/publishers/save + post + ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<Publisher> createPublisher(@Valid @RequestBody Publisher publisher)
    {
        Publisher createdPublisher = publisherService.savePublisher(publisher);
        return ResponseEntity.ok(createdPublisher);
    }

}
