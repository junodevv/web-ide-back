package goorm.webide.chat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull
    private Long userNo;

    @NotBlank(message = "채팅방 이름을 입력해야 합니다.")
    @Size(max = 20, message = "채팅방 이름은 최대 20자까지 가능합니다.")
    private String roomName;
}
