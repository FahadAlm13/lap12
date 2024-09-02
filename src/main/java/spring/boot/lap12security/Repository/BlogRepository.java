package spring.boot.lap12security.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.lap12security.Model.Blog;
import spring.boot.lap12security.Model.User;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Blog findBlogById(Integer id);

    List<Blog> findAllByUser(User user);

    Blog findBlogByTitle(String title);
}
