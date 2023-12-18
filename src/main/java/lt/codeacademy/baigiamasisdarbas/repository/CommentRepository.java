package lt.codeacademy.baigiamasisdarbas.repository;


import lt.codeacademy.baigiamasisdarbas.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Modifying
    @Query("DELETE  FROM Comment u where u.commentId= :id")
    void deleteByCommentId(Long id);

    @Query("SELECT comments FROM Comment comments INNER JOIN  comments.blog blog where blog.id = :id")
    List<Comment> findCommentsByBlogId(Long id);
}
