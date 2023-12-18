package lt.codeacademy.baigiamasisdarbas.repository;

import lt.codeacademy.baigiamasisdarbas.Entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    Blog findBlogsByTitle(String title);

    @Modifying
    @Query("DELETE  FROM Blog u where u.id= :id")
    void deleteByBlogId(Long id);
}
