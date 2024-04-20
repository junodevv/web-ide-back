package goorm.webide.chat.service;

import goorm.webide.chat.dto.ChatApiResponse;
import goorm.webide.chat.dto.ChatRoomRequest;
import goorm.webide.chat.dto.ChatRoomResponse;
import goorm.webide.chat.entity.ChatRoom;
import goorm.webide.chat.entity.ChatUser;
import goorm.webide.chat.repository.ChatRoomRepository;
import goorm.webide.chat.repository.ChatUserRepository;
import goorm.webide.user.entity.User;
import goorm.webide.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatUserRepository chatUserRepository;

    /* 채팅방 생성 POST /chat/rooms */
    @Transactional
    public ChatApiResponse<ChatRoomResponse> createChatRoom(ChatRoomRequest roomRequest) {
        ChatRoom chatRoom = ChatRoom.builder()
                .roomName(roomRequest.getRoomName())
                .createdAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
        chatRoomRepository.save(chatRoom);

        User user = userRepository.findById(roomRequest.getUserNo())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        ChatUser chatUser = ChatUser.builder()
                .chatRoom(chatRoom)
                .user(user)
                .build();
        chatUserRepository.save(chatUser);

        ChatRoomResponse roomResponse = new ChatRoomResponse(
                roomRequest.getUserNo(),
                chatRoom.getRoomNo(),
                chatRoom.getRoomName(),
                chatRoom.getCreatedAt(),
                chatRoom.getUpdateAt());
        return ChatApiResponse.success(roomResponse, "채팅방이 생성되었습니다.");
    }

    /* 전체 채팅방 목록 조회 GET /chat/rooms */
    @Transactional(readOnly = true)
    public ChatApiResponse<List<ChatRoomResponse>> findAllRooms() {
         List<ChatRoomResponse> roomResponse = chatRoomRepository.findAll().stream()
                .map(room -> new ChatRoomResponse(
                        room.getRoomNo(),
                        room.getRoomName(),
                        room.getCreatedAt(),
                        room.getUpdateAt()))
                .toList();
        return ChatApiResponse.success(roomResponse, "전체 채팅방 목록 조회에 성공했습니다.");
    }

    /* 회원별 채팅방 목록 조회 GET /chat/rooms */
    @Transactional(readOnly = true)
    public ChatApiResponse<List<ChatRoomResponse>> findAllRoomsByUserId(Long userNo) {
        List<ChatRoomResponse> roomResponse = chatUserRepository.findByUserUserNo(userNo).stream()
                .map(chatUser -> new ChatRoomResponse(
                        chatUser.getUser().getUserNo(),
                        chatUser.getChatRoom().getRoomNo(),
                        chatUser.getChatRoom().getRoomName(),
                        chatUser.getChatRoom().getCreatedAt(),
                        chatUser.getChatRoom().getUpdateAt()))
                .toList();
        return ChatApiResponse.success(roomResponse, "채팅방 목록 조회에 성공했습니다.");
    }
}
