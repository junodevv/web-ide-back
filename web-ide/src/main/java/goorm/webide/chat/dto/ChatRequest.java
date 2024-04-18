package goorm.webide.chat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : goorm.webide.chat.dto
 * fileName       : ChatRequest
 * author         : won
 * date           : 2024/04/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/14        won       최초 생성
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    @NotNull
    private Long userNo;

    @NotBlank(message = "채팅 메시지를 입력해야 합니다.")
    private String chatTxt;
}
