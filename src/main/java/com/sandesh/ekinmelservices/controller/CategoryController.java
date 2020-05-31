package com.sandesh.ekinmelservices.controller;

import com.sandesh.ekinmelservices.model.Category;
import com.sandesh.ekinmelservices.interfaces.CategoryView;
import com.sandesh.ekinmelservices.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/view")
    public ResponseEntity<List<CategoryView>> getAllCategoriesView() {
        List<CategoryView> cats = categoryService.getAllCategoriesNameOnly();
        return ResponseEntity.status(HttpStatus.OK).body(cats);
    }
}
