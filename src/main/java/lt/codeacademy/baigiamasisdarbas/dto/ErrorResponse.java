package lt.codeacademy.baigiamasisdarbas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ErrorResponse {

    private String message;
}