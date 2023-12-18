package lt.codeacademy.baigiamasisdarbas.controller;

import jakarta.validation.Valid;
import lt.codeacademy.baigiamasisdarbas.dto.BlogCreateRequest;
import lt.codeacademy.baigiamasisdarbas.dto.BlogDTO;
import lt.codeacademy.baigiamasisdarbas.dto.BlogUpdateRequest;
import lt.codeacademy.baigiamasisdarbas.Entity.Blog;
import lt.codeacademy.baigiamasisdarbas.exception.AppException;
import lt.codeacademy.baigiamasisdarbas.repository.BlogRepository;
import lt.codeacademy.baigiamasisdarbas.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogRepository blogRepository;
    private final BlogService blogService;

    public BlogController(BlogRepository blogRepository, BlogService blogService) {
        this.blogRepository = blogRepository;
        this.blogService = blogService;
    }

    @GetMapping
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @PostMapping
    public void save(@RequestBody @Valid BlogCreateRequest rq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new AppException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        blogService.addBlog(rq);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        blogService.deleteBlog(id);
    }

    @PutMapping(path = "/{id}")
    public void update(@RequestBody @Valid BlogUpdateRequest rq, BindingResult bindingResult, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            throw new AppException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        blogService.updateBlog(rq, id);
    }

    @GetMapping(path = "/{id}")
    public BlogDTO getBlog(@PathVariable Long id) {
        return blogService.getBlog(id);
    }


}