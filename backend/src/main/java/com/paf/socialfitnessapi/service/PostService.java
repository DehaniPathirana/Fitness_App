package com.paf.socialfitnessapi.service;

import com.paf.socialfitnessapi.controller.request.PostRequest;
import com.paf.socialfitnessapi.exception.NotFoundException;
import com.paf.socialfitnessapi.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    String savePost(Long userId, PostRequest postRequest)throws NotFoundException;

    List<Post> getAllPosts(Long userId)throws NotFoundException;

    List<Post> getUsersPostByPostId(Long userId,Long postId)throws NotFoundException;

    Post getPostById(Long postId);
}
