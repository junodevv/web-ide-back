package goorm.webide.user.service;

import goorm.webide.user.dto.request.RegisterRequest;
import goorm.webide.user.dto.response.RegisterResponse;
import goorm.webide.user.entity.User;
import goorm.webide.user.repository.UserRepository;
import goorm.webide.user.util.EntityDtoMapper;
import goorm.webide.user.util.enums.RegisterResult;
import goorm.webide.user.util.exception.DuplicateException;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public RegisterResponse registerUser(RegisterRequest registerRequest){
        checkEmail(registerRequest.getEmail());
        User user = EntityDtoMapper.registerReqToUser(registerRequest);
        User saveResult = repository.save(user);
        // 성공
        return EntityDtoMapper.userToRegisterResponse(saveResult);
    }

    private void checkEmail(String email){
        if(repository.findUserByEmail(email).isPresent()){
            throw new DuplicateException(
                        RegisterResult.FAIL.getResult(), RegisterResult.FAIL.getMessage(),
                        Arrays.asList("이미 사용 중인 이메일입니다."));
        }
    }
}
