package goorm.webide.chat.service;

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
import java.util.Optional;
import java.util.Set;
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

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatUserRepository chatUserRepository;

    /* 채팅방 생성(POST /chat/rooms) */
    @Transactional
    public ChatRoomResponse createChatRoom(ChatRoomRequest roomRequest) {
        // 채팅방 생성
        ChatRoom chatRoom = ChatRoom.builder()
                .roomName(roomRequest.getRoomName())
                .createdAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
        chatRoomRepository.save(chatRoom);

        // 사용자 정보 조회
        User user = userRepository.findById(roomRequest.getUserNo())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 채팅방과 사용자를 연결하는 ChatUser 객체 생성 및 저장
        ChatUser chatUser = ChatUser.builder()
                .chatRoom(chatRoom)
                .user(user)
                .build();
        chatUserRepository.save(chatUser);

        return new ChatRoomResponse(
                roomRequest.getUserNo(),
                chatRoom.getRoomNo(),
                chatRoom.getRoomName(),
                chatRoom.getCreatedAt(),
                chatRoom.getUpdateAt());
    }

    /* 채팅방 목록 조회(GET /chat/rooms) */
    // TODO: 채팅방 목록 조회 기능을 회원이 속한 채팅방만을 조회하는 기능으로 수정
    @Transactional(readOnly = true)
    public List<ChatRoomResponse> findAllRoomsByUserId(Long userNo) {
        List<ChatUser> chatUsers = chatUserRepository.findByUserUserNo(userNo);

        return chatUsers.stream()
                .map(chatUser -> new ChatRoomResponse(
                        chatUser.getUser().getUserNo(),
                        chatUser.getChatRoom().getRoomNo(),
                        chatUser.getChatRoom().getRoomName(),
                        chatUser.getChatRoom().getCreatedAt(),
                        chatUser.getChatRoom().getUpdateAt()))
                .collect(Collectors.toList());
    }
}
