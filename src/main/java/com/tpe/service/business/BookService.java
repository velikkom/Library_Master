package com.tpe.service.business;

import com.tpe.entity.business.Author;
import com.tpe.entity.business.Book;
import com.tpe.entity.business.Category;
import com.tpe.entity.business.Publisher;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.payload.messages.ErrorMessages;
import com.tpe.payload.request.business.BookRequest;
import com.tpe.payload.response.business.BookResponse;
import com.tpe.repository.business.AuthorRepository;
import com.tpe.repository.business.BookRepository;
import com.tpe.repository.business.CategoryRepository;
import com.tpe.repository.business.PublisherRepository;
import com.tpe.service.helper.ValidationUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private final PublisherRepository publisherRepository;

    private final CategoryRepository categoryRepository;

    private final ValidationUtil validationUtil;

    public Page<BookResponse> getBooks(String q, Long cat, Long author, Long publisher, int page, int size, String sort, String type) {
        Sort.Direction direction = Sort.Direction.fromString(type);
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sort));
        Page<Book> bookPage = bookRepository.searchBooks(q, cat, author, publisher, pageRequest);
        return bookPage.map(this::mapToBookResponse);
    }


    private BookResponse mapToBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .isbn(book.getIsbn())
                .authorName(book.getAuthor().getName())
                .publisherName(book.getPublisher().getName())
                .categoryName(book.getCategory().getName())
                .pageCount(book.getPageCount())
                .publishDate(book.getPublishDate())
                .loanable(book.isLoanable())
                .shelfCode(book.getShelfCode())
                .active(book.isActive())
                .featured(book.isFeatured())
                .createDate(book.getCreateDate())
                .builtIn(book.isBuiltIn())
                .build();
    }

    public BookResponse findBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.BOOK_NOT_FOUND));
        return mapToBookResponse(book);
    }


    public BookResponse createBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setName(bookRequest.getName());
        book.setIsbn(bookRequest.getIsbn());

        // Author check
        book.setAuthor(authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found")));

        // publisher check
        book.setPublisher(publisherRepository.findById(bookRequest.getPublisherId())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found")));

        // cathagory check
        book.setCategory(categoryRepository.findById(bookRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found")));

        book.setPageCount(bookRequest.getPageCount());
        book.setPublishDate(bookRequest.getPublishDate());

        if (bookRequest.getImage() != null) {
            book.setImage(bookRequest.getImage());
        }

        book.setShelfCode(bookRequest.getShelfCode());
        book.setFeatured(bookRequest.isFeatured());
        book.setActive(true);  // default
        book.setBuiltIn(false);  // default
        book.setCreateDate(LocalDateTime.now());  // default
        book.setLoanable(true);  // default

        book = bookRepository.save(book);

        return mapToBookResponse(book);
    }


    public static void validateShelfCode(String shelfCode) {
        if (shelfCode == null || !shelfCode.matches("WF-\\d{3}")) {
            throw new IllegalArgumentException("Invalid shelf code format. It should be WF-XXX");
        }
    }

    public Book updateBook(Long id, BookRequest bookRequest) {
        validationUtil.validateName(bookRequest.getName());
        validationUtil.validateISBN(bookRequest.getIsbn());
        validateShelfCode(bookRequest.getShelfCode());

        Optional<Book> existingBookOptional = bookRepository.findById(id);
        if (existingBookOptional.isEmpty()) {
            throw new IllegalArgumentException("Book not found");
        }

        Book existingBook = existingBookOptional.get();
        existingBook.setName(bookRequest.getName());
        existingBook.setIsbn(bookRequest.getIsbn());
        existingBook.setPageCount(bookRequest.getPageCount());
        existingBook.setPublishDate(bookRequest.getPublishDate());
        existingBook.setImage(bookRequest.getImage());
        existingBook.setShelfCode(bookRequest.getShelfCode());
        existingBook.setActive(bookRequest.isActive());
        existingBook.setFeatured(bookRequest.isFeatured());

        Author author = authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        existingBook.setAuthor(author);

        Publisher publisher = publisherRepository.findById(bookRequest.getPublisherId())
                .orElseThrow(() -> new IllegalArgumentException("Publisher not found"));
        existingBook.setPublisher(publisher);

        Category category = categoryRepository.findById(bookRequest.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        existingBook.setCategory(category);

        return bookRepository.save(existingBook);
    }



    /*public Book updateBook(BookRequest bookRequest, Long id) {
      ValidationUtil.validateName(bookRequest.getName());
      ValidationUtil.validateISBN(bookRequest.getIsbn());
      validateShelfCode(bookRequest.getShelfCode());

      Optional<Book> existingBookOptional = bookRepository.findById(id);
      ValidationUtil.checkIfExists(existingBookOptional,"Book");

      Book existingBook = existingBookOptional.get();
        existingBook.setName(bookRequest.getName());
        existingBook.setIsbn(bookRequest.getIsbn());
        existingBook.setPageCount(bookRequest.getPageCount());
        existingBook.setPublishDate(bookRequest.getPublishDate());
        existingBook.setImage(bookRequest.getImage());
        existingBook.setShelfCode(bookRequest.getShelfCode());
        existingBook.setActive(bookRequest.isActive());
        existingBook.setFeatured(bookRequest.isFeatured());

        Author author = authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        existingBook.setAuthor(author);

        Publisher publisher = publisherRepository.findById(bookRequest.getPublisherId())
                .orElseThrow(() -> new IllegalArgumentException("Publisher not found"));
        existingBook.setPublisher(publisher);

        Category category = categoryRepository.findById(bookRequest.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        existingBook.setCategory(category);

        return bookRepository.save(existingBook);

    }*/
}

