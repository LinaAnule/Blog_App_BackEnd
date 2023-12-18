package lt.codeacademy.baigiamasisdarbas.service;

import lt.codeacademy.baigiamasisdarbas.Entity.Blog;
import lt.codeacademy.baigiamasisdarbas.dto.BlogCreateRequest;
import lt.codeacademy.baigiamasisdarbas.dto.BlogDTO;
import lt.codeacademy.baigiamasisdarbas.dto.BlogUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface BlogService {

    void addBlog(BlogCreateRequest createRequest);

    BlogDTO getBlog(Long blogId);

    List<Blog> getBlogs();

    void updateBlog(BlogUpdateRequest request, Long id);

    void deleteBlog(Long blogId);

    Blog getByTitle(String blogTitle);

    Page<Blog> getBlogsPaginated(Pageable pageable);

}
