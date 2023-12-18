package lt.codeacademy.baigiamasisdarbas.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BlogDTO {

    private Long id;

    private String content;

    private LocalDate blogDate;

    private String title;

    private Long userId;

    private String username;

}
