package lt.codeacademy.baigiamasisdarbas.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BlogCreateRequest {

    private Long id;

    @NotEmpty(message = "blog TITLE cannot be EMPTY")
    private String title;

    @NotEmpty(message = "blog BODY cannot be EMPTY")
    private String  content;

}
