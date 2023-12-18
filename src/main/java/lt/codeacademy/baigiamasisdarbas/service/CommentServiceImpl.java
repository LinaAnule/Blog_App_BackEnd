package lt.codeacademy.baigiamasisdarbas.service;

import lt.codeacademy.baigiamasisdarbas.Entity.Blog;
import lt.codeacademy.baigiamasisdarbas.Entity.Comment;
import lt.codeacademy.baigiamasisdarbas.Entity.User;
import lt.codeacademy.baigiamasisdarbas.dto.CommentCreateRequest;
import lt.codeacademy.baigiamasisdarbas.dto.CommentDTO;
import lt.codeacademy.baigiamasisdarbas.dto.CommentUpdateRequest;
import lt.codeacademy.baigiamasisdarbas.exception.AppException;
import lt.codeacademy.baigiamasisdarbas.repository.BlogRepository;
import lt.codeacademy.baigiamasisdarbas.repository.CommentRepository;
import lt.codeacademy.baigiamasisdarbas.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(BlogRepository blogRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CommentDTO> findComments(Long blogId) {
        List<CommentDTO> commentDTOS = new ArrayList<>();
        try {
            List<Comment> comments = commentRepository.findCommentsByBlogId(blogId);
            for (Comment comment : comments
            ) {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setCommentId(comment.getCommentId());
                commentDTO.setCommentContent(comment.getCommentContent());
                commentDTO.setCommentDate(comment.getCommentDate());
                User user = userRepository.findUserByCommentId(comment.getCommentId());
                commentDTO.setUserId(user.getUserId());
                commentDTO.setUsername(user.getUsername());
                commentDTOS.add(commentDTO);
            }
        } catch (Exception e) {
            throw new AppException("Error while retrieving Comments", HttpStatus.NOT_FOUND);
        }
        return commentDTOS;
    }

    @Transactional
    @Override
    public void save(Long blogId, CommentCreateRequest request) {
        try {
            Comment comment = new Comment();
            comment.setCommentContent(request.getCommentContent());
            comment.setUser(userRepository.findById(request.getUserId()).get());
            Blog blog = blogRepository
                    .findById(blogId)
                    .orElseThrow(() -> new AppException("Product not found", HttpStatus.NOT_FOUND));
            comment.setBlog(blog);
            commentRepository.save(comment);
        } catch (AppException e) {
            throw new AppException(e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            throw new AppException("Comment was NOT SAVED", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public void update(Long commentId, CommentUpdateRequest commentRequest) {
        try {
            Comment comment = commentRepository.findById(commentId).get();
            comment.setCommentContent(commentRequest.getCommentContent());
            Comment existingComment = commentRepository
                    .findById(commentId)
                    .orElseThrow(() -> new AppException("Comment not found", HttpStatus.NOT_FOUND));
            existingComment.setCommentContent(comment.getCommentContent());
        } catch (AppException e) {
            throw new AppException(e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            throw new AppException("Comment was NOT UPDATED", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
//        commentRepository.deleteById(id);
        commentRepository.deleteByCommentId(id);
    }
}
