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
import java.util.List;
import java.util.stream.Collectors;

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
                .updateAt(LocalDateTime.now())
                .build();
        chatRoomRepository.save(chatRoom);

        ChatRoomResponse response = new ChatRoomResponse(
                chatRoom.getRoomNo(),
                chatRoom.getRoomName(),
                chatRoom.getCreatedAt(),
                chatRoom.getUpdateAt());
        return response;
    }

    /* 채팅방 목록 조회(GET /chat/rooms) */
    // TODO: 채팅방 목록 조회 기능을 회원이 속한 채팅방만을 조회하는 기능으로 수정
    @Transactional(readOnly = true)
    public List<ChatRoomResponse> findAllRooms() {
        return chatRoomRepository.findAll().stream()
                .map(room -> new ChatRoomResponse(
                        room.getRoomNo(),
                        room.getRoomName(),
                        room.getCreatedAt(),
                        room.getUpdateAt()))
                .collect(Collectors.toList());
    }
}
