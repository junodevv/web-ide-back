package goorm.webide.chat.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : goorm.webide.chat.dto
 * fileName       : ChatRoomRequest
 * author         : won
 * date           : 2024/04/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/15        won       최초 생성
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomRequest {
    @NotBlank(message = "채팅방 이름을 입력해야 합니다.")
    private String roomName;
}
