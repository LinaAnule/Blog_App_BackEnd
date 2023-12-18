package lt.codeacademy.baigiamasisdarbas.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false, unique = true)
    private Long commentId;

    @Column(name = "comment_content", nullable = false, length = 10000)
    private String commentContent;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_date", nullable = false)
    private LocalDate commentDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "blog_id", referencedColumnName = "blog_id")
    private Blog blog;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
