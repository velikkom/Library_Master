package com.tpe.payload.request.business;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@Builder
public class BookRequest {


    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "ISBN is required")
    @Pattern(regexp = "\\d{3}-\\d{2}-\\d{5}-\\d{2}-\\d", message = "Invalid ISBN format")
    private String isbn;

    private Integer pageCount;

    @NotNull(message = "Author ID is required")
    private Long authorId;

    @NotNull(message = "Publisher ID is required")
    private Long publisherId;

    private Integer publishDate;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    private String image;

    @NotEmpty(message = "Shelf code is required")
    @Pattern(regexp = "[A-Z]{2}-\\d{3}", message = "Invalid shelf code format")
    private String shelfCode;

    @NotNull(message = "Featured status is required")
    private boolean featured;

    @Column(nullable = false)
    private boolean active = true;


}
