package com.sandesh.ekinmelservices.repository;

import com.sandesh.ekinmelservices.model.CategoryDetail;
import com.sandesh.ekinmelservices.model.CategoryFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryFilterRepository extends JpaRepository<CategoryFilter, Integer> {

    List<CategoryFilter> findByCategoryDetail(CategoryDetail categoryDetailId);
}
