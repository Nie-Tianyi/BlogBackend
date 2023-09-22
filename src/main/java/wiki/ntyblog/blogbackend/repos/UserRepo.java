package wiki.ntyblog.blogbackend.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wiki.ntyblog.blogbackend.dao.User;

import java.util.List;
import java.util.UUID;
@Repository
public interface UserRepo extends CrudRepository<User, UUID> {
    List<User> findByUsername(String username);
    User save(User user);
}
