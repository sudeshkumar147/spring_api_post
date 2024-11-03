package com.infofy.infofy.service;

import com.infofy.infofy.model.Post;
import com.infofy.infofy.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public boolean deletePostById(Long id) {
        if(postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }
}
