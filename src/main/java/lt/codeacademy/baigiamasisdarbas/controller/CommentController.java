package lt.codeacademy.baigiamasisdarbas.controller;

import jakarta.validation.Valid;
import lt.codeacademy.baigiamasisdarbas.dto.CommentCreateRequest;
import lt.codeacademy.baigiamasisdarbas.dto.CommentDTO;
import lt.codeacademy.baigiamasisdarbas.dto.CommentUpdateRequest;
import lt.codeacademy.baigiamasisdarbas.exception.AppException;
import lt.codeacademy.baigiamasisdarbas.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/blogs")
public class CommentController {
    private final CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(path = "/{id}/comments")
    public List<CommentDTO> findAll(@PathVariable Long id) {
        return commentService.findComments(id);
    }

    @PostMapping(path = "/{id}/new-comment")
    public void addNewComment(@PathVariable Long id, @RequestBody @Valid CommentCreateRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new AppException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        commentService.save(id, request);
    }

    @PostMapping(path = "/{id}/comments/{commentId}")
    public void updateComment(@PathVariable Long commentId, @RequestBody @Valid CommentUpdateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new AppException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        commentService.update(commentId, request);
    }

    @DeleteMapping(path = "/{id}/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.delete(commentId);
    }
}
