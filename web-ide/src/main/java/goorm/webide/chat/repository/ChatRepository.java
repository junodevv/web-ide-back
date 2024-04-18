package goorm.webide.chat.repository;

import goorm.webide.chat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : goorm.webide.chat.repository
 * fileName       : ChatRepository
 * author         : won
 * date           : 2024/04/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/14        won       최초 생성
 */

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
}
