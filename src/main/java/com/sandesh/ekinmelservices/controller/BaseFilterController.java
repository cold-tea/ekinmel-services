package com.sandesh.ekinmelservices.controller;

import com.sandesh.ekinmelservices.model.BaseFilter;
import com.sandesh.ekinmelservices.service.BaseFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/filter/base")
public class BaseFilterController {

    @Autowired
    private BaseFilterService baseFilterService;

    @GetMapping
    public List<BaseFilter> getAllBaseFilters() {
        return baseFilterService.getAllBaseFilters();
    }
}
