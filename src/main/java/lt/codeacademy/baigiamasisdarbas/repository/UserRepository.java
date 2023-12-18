package lt.codeacademy.baigiamasisdarbas.repository;

import lt.codeacademy.baigiamasisdarbas.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    @Query("SELECT user FROM User user INNER JOIN  user.comments comment where comment.commentId = :id")
    User findUserByCommentId(Long id);
    @Query("SELECT user FROM User user INNER JOIN  user.blogs blog where blog.id = :id")
    User findUserByBlogId(Long id);
    @Query("SELECT user.userId FROM User user INNER JOIN  user.blogs blog where blog.id = :id")
    Long findIdByBlogId(Long id);
}
