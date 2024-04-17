package goorm.webide.user.controller;

import goorm.webide.user.dto.request.RegisterRequest;
import goorm.webide.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/user/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegisterRequest registerRequest){
        log.info("registerRequest: {}", registerRequest);
        return ResponseEntity.ok(service.registerUser(registerRequest));
    }

}
