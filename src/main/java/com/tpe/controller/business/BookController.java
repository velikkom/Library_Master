package com.tpe.controller.business;

import com.tpe.entity.business.Book;
import com.tpe.payload.request.business.BookRequest;
import com.tpe.payload.response.business.BookResponse;
import com.tpe.service.business.BookService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController
{
    private final BookService bookService;

    //http://localhost:8080/books/books
    @GetMapping("/books")
    public ResponseEntity<Page<BookResponse>> getBooks(
        @RequestParam(required = false) String q,
        @RequestParam(required = false) Long cat,
         @RequestParam(required = false) Long author,
        @RequestParam(required = false) Long publisher,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "name") String sort,
        @RequestParam(defaultValue = "asc") String type)
    {
      Page<BookResponse> books  = bookService.getBooks(q,cat,author,publisher,page,size,sort,type);
        return ResponseEntity.ok(books);
    }

    //http://localhost:8080/books/id
    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable @Valid Long id)
    {
       BookResponse book = bookService.findBookById(id);
       return ResponseEntity.ok(book);
    }

   //http://localhost:8080/books/save +POST + json
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest bookRequest) {
        BookResponse bookResponse = bookService.createBook(bookRequest);
        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }

    //localhost:8080/books/update/1 +POST + json
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@Valid @PathVariable Long id,
                                           @RequestBody BookRequest bookRequest)
    {
     Book updatedBook  =  bookService.updateBook(id, bookRequest);
    return ResponseEntity.ok(updatedBook);
    }






}
