package goorm.webide.user.service;

import goorm.webide.user.dto.request.RegisterRequest;
import goorm.webide.user.dto.response.RegisterResponse;
import goorm.webide.user.entity.User;
import goorm.webide.user.repository.UserRepository;
import goorm.webide.user.util.EntityDtoMapper;
import goorm.webide.user.util.enums.RegisterResult;
import goorm.webide.user.util.exception.RegisterException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public RegisterResponse registerUser(RegisterRequest registerRequest){
        User user = EntityDtoMapper.registerReqToUser(registerRequest);
        User saveResult = repository.save(user);
        // 성공
        return EntityDtoMapper.userToRegisterResponse(saveResult);
    }
}
