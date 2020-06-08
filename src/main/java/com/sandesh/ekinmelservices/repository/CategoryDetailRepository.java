package com.sandesh.ekinmelservices.repository;

import com.sandesh.ekinmelservices.model.CategoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDetailRepository extends JpaRepository<CategoryDetail, Integer> {

    CategoryDetail findCategoryDetailByCode(String code);
}
