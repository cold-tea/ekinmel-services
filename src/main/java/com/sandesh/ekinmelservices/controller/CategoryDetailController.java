package com.sandesh.ekinmelservices.controller;

import com.sandesh.ekinmelservices.model.CategoryDetail;
import com.sandesh.ekinmelservices.service.CategoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/categoryDetails")
public class CategoryDetailController {

    @Autowired
    private CategoryDetailService categoryDetailService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCategoryDetailsById(@PathVariable("id") Integer id) {
        CategoryDetail categoryDetail = categoryDetailService.getCategoryDetailById(id);
        if (categoryDetail.getCategoryFilters() == null || categoryDetail.getCategoryFilters().size() == 0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No category filters for this category detail");
        return ResponseEntity.status(HttpStatus.FOUND).body(categoryDetail);
    }

    @GetMapping(value = "/code/{code}")
    public ResponseEntity<?> getCategoryDetailsByCode(@PathVariable("code") String code) {
        CategoryDetail categoryDetail = categoryDetailService.getCategoryDetailByCode(code);
        if (categoryDetail.getCategoryFilters() == null || categoryDetail.getCategoryFilters().size() == 0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No category filters for this category detail");
        return ResponseEntity.status(HttpStatus.FOUND).body(categoryDetail);
    }
}
