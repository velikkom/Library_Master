package com.tpe.controller.business;

import com.tpe.entity.business.Category;
import com.tpe.service.business.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController
{
    private final CategoryService categoryService;

    //http://localhost:8080/categories/save
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category category)
    {
       Category savedCategory = categoryService.saveCategory(category);
       return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }


}
