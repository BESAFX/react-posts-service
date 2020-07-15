package com.react.posts;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/posts")
@CrossOrigin(value = "http://localhost:3000")
public class PostRest {

    private PostRepo repo;

    public PostRest(PostRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    @ResponseBody
    public List<Post> getAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Optional<Post> get(@PathVariable String id) {
        return repo.findById(id);
    }

    @PostMapping
    @ResponseBody
    public Post post(@RequestBody Post post) {
        return repo.save(post);
    }

    @PutMapping
    @ResponseBody
    public Post put(@RequestBody Post post) {
        return repo.existsById(post.getId()) ? repo.save(post) : null;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void delete(@PathVariable(value = "id") String id) {
        if (repo.existsById(id)) repo.deleteById(id);
    }
}
