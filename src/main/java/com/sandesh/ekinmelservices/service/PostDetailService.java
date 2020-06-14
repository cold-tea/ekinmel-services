package com.sandesh.ekinmelservices.service;

import com.sandesh.ekinmelservices.model.CategoryDetail;
import com.sandesh.ekinmelservices.model.PostDetail;
import com.sandesh.ekinmelservices.repository.PostDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostDetailService{

    @Autowired
    private PostDetailRepository postDetailRepository;

    public List<PostDetail> getAllPostDetails() {
        return postDetailRepository.findAll();
    }

    public Page<PostDetail> getAllPostDetailsWithPaging(Integer page) {
        return postDetailRepository.findAll(PageRequest.of(page, 5, Sort.Direction.ASC, "id"));
    }

    public List<PostDetail> getAllPostDetailsByCategoryDetail(CategoryDetail categoryDetail) {
        return postDetailRepository.findByCategoryDetail(categoryDetail);
    }

    public Page<PostDetail> getAllPostDetailsByCategoryDetailWithPaging(CategoryDetail categoryDetail, Integer page) {
        return postDetailRepository.findByCategoryDetail(categoryDetail,
                PageRequest.of(page, 5, Sort.Direction.ASC, "id"));
    }

    public PostDetail getPostById(Integer id) {
        return postDetailRepository.findById(id).orElse(null);
    }
}
