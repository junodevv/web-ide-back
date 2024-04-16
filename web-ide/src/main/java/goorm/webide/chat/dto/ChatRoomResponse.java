package goorm.webide.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : goorm.webide.chat.dto
 * fileName       : ChatRoomResponse
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
public class ChatRoomResponse {
    private Long roomNo;
    private String roomName;
    private LocalDateTime createdAt;
}
