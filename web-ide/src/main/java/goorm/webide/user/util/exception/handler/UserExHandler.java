package goorm.webide.user.util.exception.handler;

import goorm.webide.user.dto.response.RegisterResponse;
import goorm.webide.user.util.exception.RegisterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "goorm.webide.user")
public class UserExHandler {

    @ExceptionHandler(RegisterException.class)
    public ResponseEntity RegisterExHandler(RegisterException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(RegisterResponse.builder()
                        .success(e.getSuccess())
                        .message(e.getMessage())
                        .errors(e.getErrors())
                        .build());
    }
}
