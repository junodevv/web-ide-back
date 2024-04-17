package goorm.webide.chat.repository;

import goorm.webide.chat.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : goorm.webide.chat.repository
 * fileName       : ChatUserRepository
 * author         : won
 * date           : 2024/04/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/17        won       최초 생성
 */

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {

    List<ChatUser> findByUserUserNo(Long userNo);
}
