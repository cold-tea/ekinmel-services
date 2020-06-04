package com.sandesh.ekinmelservices.service;

import com.sandesh.ekinmelservices.model.CategoryFilter;
import com.sandesh.ekinmelservices.repository.CategoryFilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryFilterService {

    @Autowired
    private CategoryFilterRepository categoryFilterRepository;

    public List<CategoryFilter> getAllCategoryFilters() {
        return categoryFilterRepository.findAll();
    }
}
