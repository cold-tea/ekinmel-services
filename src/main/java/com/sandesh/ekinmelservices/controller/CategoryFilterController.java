package com.sandesh.ekinmelservices.controller;

import com.sandesh.ekinmelservices.model.CategoryFilter;
import com.sandesh.ekinmelservices.service.CategoryFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/filters/category")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class CategoryFilterController {

    @Autowired
    private CategoryFilterService categoryFilterService;

    @GetMapping
    public ResponseEntity<?> getAllCategoryFilters() {
        List<CategoryFilter> categoryFilters = categoryFilterService.getAllCategoryFilters();
        return ResponseEntity.status(HttpStatus.OK).body(categoryFilters);
    }
}
