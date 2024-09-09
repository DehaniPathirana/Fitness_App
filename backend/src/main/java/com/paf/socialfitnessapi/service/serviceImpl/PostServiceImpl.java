package com.paf.socialfitnessapi.service.serviceImpl;

import com.paf.socialfitnessapi.controller.request.PostRequest;
import com.paf.socialfitnessapi.exception.NotFoundException;
import com.paf.socialfitnessapi.model.Post;
import com.paf.socialfitnessapi.model.User;
import com.paf.socialfitnessapi.repository.PostRepository;
import com.paf.socialfitnessapi.repository.UserRepository;
import com.paf.socialfitnessapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl  implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public String savePost(Long userId, PostRequest postRequest) throws NotFoundException {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User Not Found With Id : " + userId)
        );

        Post post = new Post();
        post.setUser(user);
        post.setDescription(postRequest.getPostDescription());

        List<MultipartFile> images = postRequest.getImages();
        if (images != null && !images.isEmpty()) {
            try {
                // Assuming you want to store only the first image
                post.setImageData(images.get(0).getBytes());
            } catch (IOException e) {
                // Handle the exception
            }
        }

        postRepository.save(post);

        return "Post created";
    }

    @Override
    public List<Post> getAllPosts(Long userId) throws NotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User Not Found With Id : " + userId)
        );
        return postRepository.findByUser(user);
    }

    @Override
    public List<Post> getUsersPostByPostId(Long userId, Long postId) throws NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        return postRepository.findByUserAndId(user, postId)
                .map(List::of)
                .orElseThrow(() -> new NotFoundException("Post not found with id: " + postId + " for user: " + userId));
    }

    @Override
    public Post getPostById(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            byte[] imageData = post.getImageData();
            if (imageData != null && imageData.length > 0) {
                System.out.println("Image data size: " + imageData.length);
                System.out.println("First few bytes of image data: " + Arrays.toString(Arrays.copyOfRange(imageData, 0, Math.min(imageData.length, 10))));
                return post;
            } else {
                System.out.println("Image data is empty or null");
                return post;
            }
        }else {
            return null;
        }
    }
}
