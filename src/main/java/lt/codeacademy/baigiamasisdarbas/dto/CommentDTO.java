package lt.codeacademy.baigiamasisdarbas.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentDTO {
    private Long commentId;
    private String commentContent;
    private LocalDate commentDate;
    private Long UserId;
    private String username;

}
