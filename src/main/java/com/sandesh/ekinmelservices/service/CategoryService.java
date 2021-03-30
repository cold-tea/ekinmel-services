package com.sandesh.ekinmelservices.service;

import com.sandesh.ekinmelservices.interfaces.CategoryView;
import com.sandesh.ekinmelservices.model.Category;
import com.sandesh.ekinmelservices.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<CategoryView> getAllCategoriesNameOnly() {
        List<CategoryView> cats = categoryRepository.findAllCategoryView();
        for (CategoryView cat : cats) {
            System.out.println(cat.getName());
        }
        return cats;
    }
}
