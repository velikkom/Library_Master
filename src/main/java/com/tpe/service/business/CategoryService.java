package com.tpe.service.business;

import com.tpe.entity.business.Category;
import com.tpe.repository.business.CategoryRepository;
import com.tpe.service.helper.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService
{
    private final CategoryRepository categoryRepository;



    public Category saveCategory(Category category)
    {
        ValidationUtil.validateName(category.getName());
        Optional<Category> existingCategory = categoryRepository.findByName(category.getName());
        ValidationUtil.checkIfExists(existingCategory,"Category");
        return categoryRepository.save(category);
    }


}
