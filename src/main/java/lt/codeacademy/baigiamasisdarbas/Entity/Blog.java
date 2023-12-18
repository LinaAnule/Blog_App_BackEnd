package lt.codeacademy.baigiamasisdarbas.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "title", nullable = false, length = 1000)
    @NotNull(message = "title cannot be empty")
    @Length
    private String title;

    @Column(name = "content", nullable = false, length=200000)
    @NotNull
    private String content;


    @CreationTimestamp
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Temporal(TemporalType.DATE)
    @Column(name = "blog_date", nullable = false)
    private LocalDate blogDate;

    @JsonIgnore
    @OneToMany(mappedBy = "blog", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Comment> comments;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

}
