package com.spring.controller;

import com.spring.dto.PostDTO;
import com.spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public ResponseEntity<?> posts(){
        return ResponseEntity.ok(postService.posts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> post(@PathVariable Long id){
        return ResponseEntity.ok(postService.post(id));
    }

    @PostMapping("")
    public ResponseEntity<?> addPost(@RequestBody PostDTO postDTO){
        return ResponseEntity.ok(postService.addPost(postDTO));
    }

    @PutMapping("")
    public ResponseEntity<?> updatePost(@RequestBody PostDTO postDTO){
        postService.updatePost(postDTO);
        return ResponseEntity.ok(null);
    }
}
