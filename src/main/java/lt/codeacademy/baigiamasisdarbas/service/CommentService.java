package lt.codeacademy.baigiamasisdarbas.service;

import lt.codeacademy.baigiamasisdarbas.dto.CommentCreateRequest;
import lt.codeacademy.baigiamasisdarbas.dto.CommentDTO;
import lt.codeacademy.baigiamasisdarbas.dto.CommentUpdateRequest;

import java.util.List;

public interface CommentService {
    List<CommentDTO> findComments(Long blogId);

    void save(Long blogId, CommentCreateRequest request);

    void delete(Long id);

    void update(Long commentId, CommentUpdateRequest commentRequest);
}
