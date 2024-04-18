package goorm.webide.user.controller;

import goorm.webide.user.dto.request.RegisterRequest;
import goorm.webide.user.service.UserService;
import goorm.webide.user.util.enums.RegisterResult;
import goorm.webide.user.util.exception.RegisterException;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/user/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegisterRequest registerRequest,
                                       BindingResult bindingResult
    ){
        if(!registerRequest.getPassword().equals(registerRequest.getPasswordConfirm())){
            bindingResult.addError( new FieldError(
                            "password_confirmation",
                            "passwordConfirm",
                            "비밀번호와 비밀번호 확인이 일치하지 않습니다.")
            );
        }
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .toList();
            throw new RegisterException(
                    RegisterResult.REGISTER_FAIL.getResult(),RegisterResult.REGISTER_FAIL.getMessage(), errors
            );
        }
        return ResponseEntity.ok(service.registerUser(registerRequest));
    }

}
