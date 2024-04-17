package goorm.webide.user.util;

import goorm.webide.user.dto.UserDto;
import goorm.webide.user.dto.request.RegisterRequest;
import goorm.webide.user.dto.response.RegisterResponse;
import goorm.webide.user.entity.User;

public class EntityDtoMapper {
    /**
     * 회원가입 요청 -> User Entity
     * @param registerRequest
     * @return User
     */
    public static User registerReqToUser(RegisterRequest registerRequest){
        return User.builder()
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .email(registerRequest.getEmail())
                .nickname(registerRequest.getNickname())
                .build();
    }
    /**
     * User Entity -> 회원가입 응답
     * @param user
     * @return RegisterResponse
     */
    public static RegisterResponse userToRegisterResponse(User user){
        return RegisterResponse.builder()
                .success(true)
                .message("회원가입이 성공적으로 완료되었습니다.")
                .user(userToDto(user))
                .build();
    }
    /**
     * User Entity -> UserDto
     * @param user
     * @return UserDto
     */
    public static UserDto userToDto(User user){
        return UserDto.builder()
                .userNo(user.getUserNo())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }
}
