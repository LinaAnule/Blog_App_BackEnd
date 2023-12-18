package lt.codeacademy.baigiamasisdarbas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentUpdateRequest {

    private Long userId;

    @NotNull(message = "Comment BODY cannot be EMPTY")
    private String commentContent;
}
