package com.sandesh.ekinmelservices.controller;

import com.sandesh.ekinmelservices.model.CategoryDetail;
import com.sandesh.ekinmelservices.model.CategoryFilter;
import com.sandesh.ekinmelservices.service.CategoryDetailService;
import com.sandesh.ekinmelservices.service.CategoryFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/filters/category")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class CategoryFilterController {

    @Autowired
    private CategoryFilterService categoryFilterService;

    @Autowired
    private CategoryDetailService categoryDetailService;

    @GetMapping
    public ResponseEntity<?> getAllCategoryFilters() {
        List<CategoryFilter> categoryFilters = categoryFilterService.getAllCategoryFilters();
        return ResponseEntity.status(HttpStatus.OK).body(categoryFilters);
    }

    @GetMapping("/categoryDetail/{categoryDetailId}")
    public ResponseEntity<?> getCategoryFiltersByCategoryDetail(@PathVariable Integer categoryDetailId) {
        CategoryDetail categoryDetail = categoryDetailService.getCategoryDetailById(categoryDetailId);
        if (categoryDetail == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No category detail found");
        List<CategoryFilter> categoryFilters = categoryFilterService.getCategoryFiltersByCategoryDetail(categoryDetail);
        if (categoryFilters.size() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No category filters for this category");
        return ResponseEntity.status(HttpStatus.OK).body(categoryFilters);
    }
}
