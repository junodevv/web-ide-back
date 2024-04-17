package goorm.webide.user.dto.response;

import goorm.webide.user.dto.UserDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterResponse {
    private Boolean success;
    private String message;
    private UserDto user;
}

/**
 * {
 *     "success": true,
 *     "message": "회원가입이 성공적으로 완료되었습니다.",
 *     "user": {
 *         "userNo": "1",
 *         "email": "test123@gmail.com",
 *         "nickname": "leejuno"
 *     }
 * }
 */