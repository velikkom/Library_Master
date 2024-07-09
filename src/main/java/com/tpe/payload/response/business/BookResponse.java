package com.tpe.payload.response.business;

import com.tpe.entity.business.Author;
import com.tpe.entity.business.Category;
import com.tpe.entity.business.Publisher;
import lombok.*;


import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)

public class BookResponse {

    private Long id;
    private String name;
    private String isbn;
    private String authorName;
    private String publisherName;
    private String categoryName;
    private Integer pageCount;
    private Integer publishDate;
    private boolean loanable;
    private String shelfCode;
    private boolean active;
    private boolean featured;
    private LocalDateTime createDate;
    private boolean builtIn;
    private String image;

}
