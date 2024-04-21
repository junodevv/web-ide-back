package goorm.webide.user.util.exception;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterException extends RuntimeException{
    private Boolean success;
    private String message;
    private List<String> errors;
}
