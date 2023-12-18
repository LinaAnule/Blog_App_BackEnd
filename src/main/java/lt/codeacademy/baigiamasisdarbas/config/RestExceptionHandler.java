package lt.codeacademy.baigiamasisdarbas.config;

import lt.codeacademy.baigiamasisdarbas.dto.ErrorResponse;
import lt.codeacademy.baigiamasisdarbas.exception.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(AppException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(ErrorResponse.builder().message(ex.getMessage()).build());
    }
}
