package com.sandesh.ekinmelservices.repository;

import com.sandesh.ekinmelservices.model.Category;
import com.sandesh.ekinmelservices.interfaces.CategoryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select c.name as name from Category c")
    List<CategoryView> findAllCategoryView();
}
