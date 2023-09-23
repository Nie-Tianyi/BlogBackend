package wiki.ntyblog.blogbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import wiki.ntyblog.blogbackend.dao.Blog;
import wiki.ntyblog.blogbackend.repos.BlogRepo;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Service
public class BlogService {
    private final BlogRepo blogRepo;

    public BlogService(BlogRepo blogRepo) {
        this.blogRepo = blogRepo;
    }

    public void createBlog(Blog blog) {
        blogRepo.save(blog);
    }

    public List<UUID> getAllPostsId(){
        return blogRepo.findAllIds();
    }

    public List<Blog> getAllPosts(){

        return blogRepo.findAll();
    }

    public Blog getPostById(String postId){
        return blogRepo.findBlogById(UUID.fromString(postId)).get(0);
    }


}
