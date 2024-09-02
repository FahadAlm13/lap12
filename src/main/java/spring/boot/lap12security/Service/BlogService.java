package spring.boot.lap12security.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.lap12security.API.ApiException;
import spring.boot.lap12security.Model.Blog;
import spring.boot.lap12security.Model.User;
import spring.boot.lap12security.Repository.AuthRepository;
import spring.boot.lap12security.Repository.BlogRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getAllBlogs() {

        return blogRepository.findAll();
    }

    public List<Blog> getBlog(Integer id) {

        User user = authRepository.findUserById(id);

        return blogRepository.findAllByUser(user);
    }

    public void addBlog(Integer userId, Blog blog) {
        User user = authRepository.findUserById(userId);

        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer user_id, Blog blog, Integer blog_id) {
        User user = authRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("User not found");
        }
        Blog blog1 = blogRepository.findBlogById(blog_id);
        if (blog1 == null) {
            throw new ApiException("Blog not found");
        } else if (blog1.getUser().getId() != user_id) {
            throw new ApiException("User id not match");
        }
        blog1.setTitle(blog.getTitle());
        blog1.setBody(blog.getBody());
        blog1.setUser(user);
        blogRepository.save(blog1);
    }

    public void deleteBlog(Integer user_id, Integer blog_id) {
        User user = authRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("User not found");
        }
        Blog blog1 = blogRepository.findBlogById(blog_id);
        if (blog1 == null) {
            throw new ApiException("Blog not found");
        } else if (blog1.getUser().getId() != user_id) {
            throw new ApiException("User id not match");
        }
        blogRepository.delete(blog1);

    }

    public Blog getBlogById(Integer user_id, Integer blog_id) {
        User user = authRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("User not found");
        }
        Blog blog1 = blogRepository.findBlogById(blog_id);
        if (blog1 == null) {
            throw new ApiException("Blog not found");
        } else if (blog1.getUser().getId() != user_id) {
            throw new ApiException("User id not match");
        }
        return blog1;
    }

    public Blog getBlogByTitle(Integer user_id, String title) {
        User user = authRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("User not found");
        }
        Blog blog1 = blogRepository.findBlogByTitle(title);
        if (blog1 == null) {
            throw new ApiException("Blog not found");
        } else if (blog1.getUser().getId() != user_id) {
            throw new ApiException("User id not match");
        }
        return blog1;
    }
}
