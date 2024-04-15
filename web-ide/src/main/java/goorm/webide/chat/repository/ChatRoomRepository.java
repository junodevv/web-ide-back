package goorm.webide.chat.repository;

import goorm.webide.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : goorm.webide.chat.repository
 * fileName       : ChatRoomRepository
 * author         : won
 * date           : 2024/04/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/15        won       최초 생성
 */

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
