package com.sandesh.ekinmelservices.service;

import com.sandesh.ekinmelservices.model.BaseFilter;
import com.sandesh.ekinmelservices.repository.BaseFilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseFilterService {

    @Autowired
    private BaseFilterRepository baseFilterRepository;

    public List<BaseFilter> getAllBaseFilters() {
        return baseFilterRepository.findAll();
    }
}
