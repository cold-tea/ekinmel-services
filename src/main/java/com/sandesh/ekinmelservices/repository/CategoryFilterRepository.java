package com.sandesh.ekinmelservices.repository;

import com.sandesh.ekinmelservices.model.CategoryFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryFilterRepository extends JpaRepository<CategoryFilter, Integer> {
}
