package com.sandesh.ekinmelservices.controller;

import com.sandesh.ekinmelservices.model.CategoryDetail;
import com.sandesh.ekinmelservices.model.PostDetail;
import com.sandesh.ekinmelservices.service.CategoryDetailService;
import com.sandesh.ekinmelservices.service.PostDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/posts")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class PostDetailController {

    private static final Logger logger = LoggerFactory.getLogger(PostDetailController.class);

    @Autowired
    private PostDetailService postDetailService;

    @Autowired
    private CategoryDetailService categoryDetailService;

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        List<PostDetail> postDetails = postDetailService.getAllPostDetails();
        return ResponseEntity.status(HttpStatus.OK).body(postDetails);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Integer id) throws InterruptedException {
        PostDetail postDetail = postDetailService.getPostById(id);
        if (postDetail == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Specified post not found");
        Thread.sleep(300);
        return ResponseEntity.status(HttpStatus.OK).body(postDetail);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllPostsWillPaging(@RequestParam Optional<Integer> page) throws InterruptedException {
        Page<PostDetail> postDetails = postDetailService.getAllPostDetailsWithPaging(page.orElse(0));
        if (postDetails.getSize() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No posts found");
        Thread.sleep(300);
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

    @GetMapping(value = "/categoryDetail/{categoryDetailId}")
    public ResponseEntity<?> getAllPostsByCategoryPage(@PathVariable Integer categoryDetailId,
                                                       @RequestParam Optional<Integer> page) throws InterruptedException {
        CategoryDetail categoryDetail = categoryDetailService.getCategoryDetailById(categoryDetailId);
        if (categoryDetail == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No category detail found.");
        Page<PostDetail> postDetails = postDetailService.getAllPostDetailsByCategoryDetailWithPaging(categoryDetail, page.orElse(0));
        if (postDetails == null || postDetails.getSize() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No posts for this category");
        logger.info("Page number: " + page.orElse(0));
        Thread.sleep(300);
        return ResponseEntity.status(HttpStatus.OK).body(postDetails);
    }
}
