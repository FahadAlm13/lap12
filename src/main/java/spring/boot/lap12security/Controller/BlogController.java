package spring.boot.lap12security.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import spring.boot.lap12security.Model.Blog;
import spring.boot.lap12security.Model.User;
import spring.boot.lap12security.Service.BlogService;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get-all-blogs")
    public ResponseEntity getAllBlogs() {

        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }

    @GetMapping("/getBlog")
    public ResponseEntity getBlog(@AuthenticationPrincipal User user) {

        return ResponseEntity.status(200).body(blogService.getBlog(user.getId()));
    }

    @PostMapping("/add-blog")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog) {
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body("Successfully added blog");
    }

    @PutMapping("/update/{blogId}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog, @PathVariable Integer blogId) {
        blogService.updateBlog(user.getId(), blog, blogId);
        return ResponseEntity.status(200).body("Successfully updated blog");
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer blogId) {
        blogService.deleteBlog(user.getId(), blogId);
        return ResponseEntity.status(200).body("Successfully deleted blog");
    }

    @GetMapping("/get-blog-by/{id}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        return ResponseEntity.status(200).body(blogService.getBlogById(user.getId(), id));
    }

    @GetMapping("/get-blog-by-title/{title}")
    public ResponseEntity getBlogByTitle(@AuthenticationPrincipal User user, @PathVariable String title) {
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(user.getId(), title));
    }
}
