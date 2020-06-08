package com.sandesh.ekinmelservices.service;

import com.sandesh.ekinmelservices.model.CategoryDetail;
import com.sandesh.ekinmelservices.repository.CategoryDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryDetailService {

    @Autowired
    private CategoryDetailRepository categoryDetailRepository;

    public CategoryDetail getCategoryDetailById(Integer id) {
        return categoryDetailRepository.findById(id).orElse(null);
    }

    public CategoryDetail getCategoryDetailByCode(String code) {
        return categoryDetailRepository.findCategoryDetailByCode(code);
    }
}
