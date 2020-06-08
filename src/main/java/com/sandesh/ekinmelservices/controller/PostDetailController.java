package com.sandesh.ekinmelservices.controller;

import com.sandesh.ekinmelservices.model.CategoryDetail;
import com.sandesh.ekinmelservices.model.PostDetail;
import com.sandesh.ekinmelservices.service.CategoryDetailService;
import com.sandesh.ekinmelservices.service.PostDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/postDetails")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class PostDetailController {

    @Autowired
    private PostDetailService postDetailService;

    @Autowired
    private CategoryDetailService categoryDetailService;

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        List<PostDetail> postDetails = postDetailService.getAllPostDetails();
        return ResponseEntity.status(HttpStatus.OK).body(postDetails);
    }

    @GetMapping(value = "/categoryDetailId/{categoryDetailId}")
    public ResponseEntity<?> getAllPostsByCategory(@PathVariable Integer categoryDetailId) throws InterruptedException {
        CategoryDetail categoryDetail = categoryDetailService.getCategoryDetailById(categoryDetailId);
        if (categoryDetail == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No category detail found.");
        List<PostDetail> postDetails = postDetailService.getAllPostDetailsByCategoryDetail(categoryDetail);
        if (postDetails == null || postDetails.size() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No posts for this category");
        return ResponseEntity.status(HttpStatus.OK).body(postDetails);
    }
}
