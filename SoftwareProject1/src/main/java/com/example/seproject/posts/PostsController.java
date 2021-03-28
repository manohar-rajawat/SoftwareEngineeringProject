package com.example.seproject.posts;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class PostsController {

	protected Logger logger = Logger.getLogger(PostsController.class
			.getName());
	protected PostRepository postRepository;

	@Autowired
	public PostsController(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@GetMapping("/posts")
	public List<Post> retrieveAllPost() {
		List<Post> posts = new ArrayList<Post>();
        postRepository.findAll().forEach(post -> posts.add(post));
        return posts;
	}

	@GetMapping("/posts/{id}")
    private Post getPost(@PathVariable("id") Long id) {
        return postRepository.findById(id).get();
    }

    @DeleteMapping("/posts/{id}")
    private void deletePost(@PathVariable("id") Long id) {
		postRepository.deleteById(id);
    }

	@PostMapping("/posts")
    private Long savePost(@RequestBody Post post) {
        postRepository.save(post);
        return post.getId();
    }
}