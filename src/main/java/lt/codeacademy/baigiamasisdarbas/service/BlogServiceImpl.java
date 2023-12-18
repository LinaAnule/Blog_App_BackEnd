package lt.codeacademy.baigiamasisdarbas.service;

import lt.codeacademy.baigiamasisdarbas.Entity.Blog;
import lt.codeacademy.baigiamasisdarbas.dto.BlogCreateRequest;
import lt.codeacademy.baigiamasisdarbas.dto.BlogDTO;
import lt.codeacademy.baigiamasisdarbas.dto.BlogUpdateRequest;
import lt.codeacademy.baigiamasisdarbas.exception.AppException;
import lt.codeacademy.baigiamasisdarbas.repository.BlogRepository;
import lt.codeacademy.baigiamasisdarbas.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public BlogServiceImpl(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void addBlog(BlogCreateRequest createRequest) {
        try {
            Blog blog = new Blog();
            blog.setTitle(createRequest.getTitle());
            blog.setContent(createRequest.getContent());
            blog.setUser(userRepository.findById(createRequest.getId()).get());
            blogRepository.save(blog);
        } catch (Exception e) {
            throw new AppException("Saving blog FAILED", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public BlogDTO getBlog(Long blogId) {
        BlogDTO blogDTO = new BlogDTO();
        try {
            Blog blog = blogRepository.findById(blogId).get();
            blogDTO.setId(blog.getId());
            blogDTO.setContent(blog.getContent());
            blogDTO.setBlogDate(blog.getBlogDate());
            blogDTO.setUserId(blog.getUser().getUserId());
            blogDTO.setUsername(blog.getUser().getUsername());
            blogDTO.setTitle(blog.getTitle());
        } catch (Exception e) {
            throw new AppException("Blog not found", HttpStatus.NOT_FOUND);
        }
        return blogDTO;
    }

    @Override
    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }

    @Override
    @Transactional
    public void updateBlog(BlogUpdateRequest request, Long id) {
        try {
            Optional<Blog> blogEntity = blogRepository.findById(id);
            Blog blogEntity1 = blogEntity.get();
            blogEntity1.setTitle(request.getTitle());
            blogEntity1.setContent(request.getContent());
            if (!Objects.equals(request.getUserId(), blogEntity1.getUser().getUserId())) {
                throw new AppException("Cannot edit blog, cause you are not the author", HttpStatus.BAD_REQUEST);
            }
            blogRepository.save(blogEntity1);
        } catch (Exception e) {
            throw new AppException("Blog update failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public void deleteBlog(Long blogId) {
        try {
            blogRepository.deleteByBlogId(blogId);
        } catch (Exception e) {
            throw new AppException("Failed to delete a blog", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Blog getByTitle(String blogTitle) {
        return blogRepository.findBlogsByTitle(blogTitle);
    }

    @Override
    public Page<Blog> getBlogsPaginated(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }
}
