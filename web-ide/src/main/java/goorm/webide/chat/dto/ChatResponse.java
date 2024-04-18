package goorm.webide.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : goorm.webide.chat.dto
 * fileName       : ChatResponse
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
public class ChatResponse {
    private Long userNo;
    private Long chatRoomNo;
    private Long chatNo;
    private String chatTxt;
    private LocalDateTime createdAt;
}
