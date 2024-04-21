package goorm.webide.user.util.exception;

import java.util.List;
import lombok.Getter;

@Getter
public class DuplicateException extends RegisterException{
    public DuplicateException(Boolean success, String message, List<String> errors) {
        super(success, message, errors);
    }
}
