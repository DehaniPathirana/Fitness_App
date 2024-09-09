package com.paf.socialfitnessapi.controller;

import com.paf.socialfitnessapi.controller.request.PostRequest;
import com.paf.socialfitnessapi.exception.NotFoundException;
import com.paf.socialfitnessapi.model.Post;
import com.paf.socialfitnessapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@RestController
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("users/{userId}/posts")
    public ResponseEntity<String> createPost(@PathVariable("userId") Long userId, @RequestPart("description") String description,
                                             @RequestPart("images") List<MultipartFile> images){

        try {
            PostRequest postDto = new PostRequest(description, images);
            String responseMessage = postService.savePost(userId,postDto);
            return ResponseEntity.ok(responseMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }

    }


    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<Post>> getAllPostsByUserId(@PathVariable Long userId) throws NotFoundException {
        List<Post> posts = postService.getAllPosts(userId);
        return ResponseEntity.ok(posts);
    }


    @GetMapping("/users/{userId}/posts/{postId}/images")
    public ResponseEntity<String> getPostImageByUserIdAndPostId(@PathVariable Long userId, @PathVariable Long postId) throws NotFoundException {
        List<Post> posts = postService.getUsersPostByPostId(userId, postId);
        if (posts.isEmpty()) {
            throw new NotFoundException("Post not found with id: " + postId + " for user: " + userId);
        }
        Post post = posts.get(0);
        byte[] imageData = post.getImageData();
        if (imageData != null && imageData.length > 0) {
            String base64Image = Base64.getEncoder().encodeToString(imageData);
            return ResponseEntity.ok(base64Image);
        } else {
            return ResponseEntity.ok(null);
        }
    }


    @GetMapping("/posts/{postId}/images")
    public ResponseEntity<String> getPostImages(@PathVariable Long postId) {
        Post post = postService.getPostById(postId);

        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] imageData = post.getImageData();
        if (imageData != null && imageData.length > 0) {
            String base64Image = Base64.getEncoder().encodeToString(imageData);
            return ResponseEntity.ok(base64Image);
        } else {
            return ResponseEntity.ok(null);
        }
    }


}
