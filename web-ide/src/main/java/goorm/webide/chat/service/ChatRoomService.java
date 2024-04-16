package goorm.webide.chat.service;

import goorm.webide.chat.dto.ChatRoomRequest;
import goorm.webide.chat.dto.ChatRoomResponse;
import goorm.webide.chat.entity.ChatRoom;
import goorm.webide.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * packageName    : goorm.webide.chat.service
 * fileName       : ChatRoomService
 * author         : won
 * date           : 2024/04/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/15        won       최초 생성
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    /* 채팅방 생성(POST /chat/rooms) */
    @Transactional
    public ChatRoomResponse createChatRoom(ChatRoomRequest roomRequest) {
        ChatRoom chatRoom = ChatRoom.builder()
                .roomName(roomRequest.getRoomName())
                .createdAt(LocalDateTime.now())
                .build();
        chatRoomRepository.save(chatRoom);

        ChatRoomResponse response = new ChatRoomResponse(
                chatRoom.getRoomNo(),
                chatRoom.getRoomName(),
                chatRoom.getCreatedAt());
        return response;
    }
}
