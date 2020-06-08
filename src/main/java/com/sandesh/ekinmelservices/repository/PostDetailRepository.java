package com.sandesh.ekinmelservices.repository;

import com.sandesh.ekinmelservices.model.CategoryDetail;
import com.sandesh.ekinmelservices.model.PostDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDetailRepository extends JpaRepository<PostDetail, Integer> {

    List<PostDetail> findByCategoryDetail(CategoryDetail categoryDetail);
}
