package com.tpe.repository.business;

import com.tpe.entity.business.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book,Long>

{

    @Query("SELECT b FROM Book b WHERE " +
            "(?1 IS NULL OR b.name LIKE %?1% OR b.author.name LIKE %?1% OR b.isbn LIKE %?1% OR b.publisher.name LIKE %?1%) AND " +
            "(?2 IS NULL OR b.category.id = ?2) AND " +
            "(?3 IS NULL OR b.author.id = ?3) AND " +
            "(?4 IS NULL OR b.publisher.id = ?4) AND " +
            "b.active = true")
    Page<Book> searchBooks(String q, Long cat, Long author, Long publisher, PageRequest pageRequest);




}
