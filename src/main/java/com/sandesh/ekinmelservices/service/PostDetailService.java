package com.sandesh.ekinmelservices.service;

import com.sandesh.ekinmelservices.model.CategoryDetail;
import com.sandesh.ekinmelservices.model.PostDetail;
import com.sandesh.ekinmelservices.repository.PostDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostDetailService{

    @Autowired
    private PostDetailRepository postDetailRepository;

    public List<PostDetail> getAllPostDetails() {
        return postDetailRepository.findAll();
    }

    public List<PostDetail> getAllPostDetailsByCategoryDetail(CategoryDetail categoryDetail) {
        return postDetailRepository.findByCategoryDetail(categoryDetail);
    }
}
