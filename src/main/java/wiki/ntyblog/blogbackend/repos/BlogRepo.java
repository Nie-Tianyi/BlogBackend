package wiki.ntyblog.blogbackend.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wiki.ntyblog.blogbackend.dao.Blog;

import java.util.List;
import java.util.UUID;

@Repository
public interface BlogRepo extends CrudRepository<Blog, UUID> {
    Blog save(Blog blog);
    List<Blog> findBlogById(UUID id);
    @Query("select id from Blog")
    List<UUID> findAllIds();
}
