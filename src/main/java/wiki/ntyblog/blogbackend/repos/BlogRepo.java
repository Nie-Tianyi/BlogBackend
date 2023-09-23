package wiki.ntyblog.blogbackend.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wiki.ntyblog.blogbackend.dao.Blog;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Repository
public interface BlogRepo extends JpaRepository<Blog, UUID> {
    Blog save(Blog blog);
    List<Blog> findBlogById(UUID id);
    @Query("select id from Blog")
    List<UUID> findAllIds();
    List<Blog> findAll();
}
