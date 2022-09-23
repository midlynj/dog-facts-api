package com.example.newproject.Controllers;

import com.example.newproject.Repository.CategoryRepository;
import com.example.newproject.Repository.PostRepository;
import com.example.newproject.Repository.UserRepository;
import com.example.newproject.data.Category;
import com.example.newproject.data.Post;
import com.example.newproject.data.User;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

@AllArgsConstructor
@RestController
@RequestMapping(value ="/api/posts", produces = "application/json")
public class PostController {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;

    @GetMapping("")
        private List<Post> getAllPost() {
            List<Post> allPosts = postRepository.findAll();
            return allPosts;
        }

    @PostMapping("")
    private void addAPost(@RequestBody Post newPost, OAuth2Authentication auth) {
        String username =  auth.getName();
        User author = userRepository.findByUserName(username);
        newPost.setAuthor(author);

        newPost.setCategories(new ArrayList<>());

        // use first 2 categories for the post by default
        Category cat1 = categoryRepository.findById(3L).get();
        Category cat2 = categoryRepository.findById(2L).get();

        newPost.getCategories().add(cat1);
        newPost.getCategories().add(cat2);

        postRepository.save(newPost);
    }

    @DeleteMapping("/{id}")
        private void deleteAPost(@PathVariable Long id, OAuth2Authentication auth) {
         auth.getName();

        postRepository.deleteById(id);
    }

    @PutMapping("/{id}")
        private void editAPost(@PathVariable Long id, @RequestBody Post updatePost) {
            updatePost.setId(id);
            postRepository.save(updatePost);
    }
}
