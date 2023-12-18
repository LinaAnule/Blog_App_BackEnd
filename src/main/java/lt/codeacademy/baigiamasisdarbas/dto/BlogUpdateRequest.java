package lt.codeacademy.baigiamasisdarbas.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data

public class BlogUpdateRequest {
    private Long userId;

    @NotEmpty(message = "Blog TITLE cannot be EMPTY")
    private String title;

    @NotEmpty(message = "Blog BODY cannot be EMPTY")
    private String content;
}
