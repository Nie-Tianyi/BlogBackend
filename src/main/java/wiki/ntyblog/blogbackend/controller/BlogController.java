package wiki.ntyblog.blogbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wiki.ntyblog.blogbackend.dao.Blog;
import wiki.ntyblog.blogbackend.service.BlogService;
import wiki.ntyblog.blogbackend.service.UserService;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/backend/blog")
public class BlogController {
    private final BlogService blogService;
    private final UserService userService;

    public BlogController(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity createBlog(@RequestBody HashMap req){
        String title = (String) req.get("title");
        String content = (String) req.get("content");
        String username = (String) req.get("username");
        String password = (String) req.get("password");
        Date date = new Date();

        try {
            if (!userService.checkPassword(username, password)) {
                return ResponseEntity.badRequest().body("Invalid Username or Password");
            }
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.badRequest().body("Internal Error, Server Failed to Hash Password");
        }

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setDate(date);
        blog.setUser(userService.getUserByUsername(username));

        blogService.createBlog(blog);

        return ResponseEntity.ok("Post Successfully Created");
    }

    @GetMapping("/getAllPostsId")
    @ResponseBody
    public ResponseEntity getAllPostsId(){
        return ResponseEntity.ok(blogService.getAllPostsId());
    }

    @GetMapping("/getAllPosts")
    @ResponseBody
    public ResponseEntity getAllPosts(){
        return ResponseEntity.ok(blogService.getAllPosts());
    }

    @GetMapping("/{postId}")
    @ResponseBody
    public ResponseEntity getPostById(@PathVariable String postId){
        return ResponseEntity.ok(blogService.getPostById(postId));
    }


}
