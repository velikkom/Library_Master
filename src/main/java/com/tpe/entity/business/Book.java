package com.tpe.entity.business;

import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 17)
    private String isbn;

    private Integer pageCount;

    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisherId", nullable = false)
    private Publisher publisher;

    private Integer publishDate;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @Lob
    private String image;

    @Column(nullable = false)
    private boolean loanable = true;

    @Column(nullable = false, length = 6)
    private String shelfCode;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private boolean featured = false;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private boolean builtIn = false;




}
