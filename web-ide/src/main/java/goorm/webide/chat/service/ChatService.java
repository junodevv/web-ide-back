package goorm.webide.chat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import goorm.webide.chat.dto.ChatApiResponse;
import goorm.webide.chat.dto.ChatRequest;
import goorm.webide.chat.dto.ChatResponse;
import goorm.webide.chat.entity.Chat;
import goorm.webide.chat.entity.ChatRoom;
import goorm.webide.chat.repository.ChatRepository;
import goorm.webide.chat.repository.ChatRoomRepository;
import goorm.webide.user.entity.User;
import goorm.webide.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * packageName    : goorm.webide.chat.service
 * fileName       : ChatService
 * author         : won
 * date           : 2024/04/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/14        won       최초 생성
 */

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    /* 채팅 보내기 */
    @Transactional
    public ChatResponse saveChat(ChatRequest chatRequest, Long roomNo) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(chatRequest);

        ChatRoom chatRoom = chatRoomRepository.findById(roomNo)
                .orElseThrow(() -> new IllegalArgumentException("채팅방을 찾을 수 없습니다."));
        User user = userRepository.findById(chatRequest.getUserNo())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Chat chat = Chat.builder()
                .chatTxt(json)
                .createdAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .chatRoom(chatRoom)
                .user(user)
                .build();
        chatRepository.save(chat);

        return new ChatResponse(
                chat.getChatNo(),
                chatRoom.getRoomNo(),
                user.getUserNo(),
                chat.getChatTxt(),
                chat.getCreatedAt(),
                chat.getUpdateAt()
        );
    }

    /* 채팅 불러오기 GET /chat/rooms/{roomNo} */
    @Transactional(readOnly = true)
    public ChatApiResponse<Page<ChatResponse>> getAllChatsByRoomNo(Long roomNo, Pageable pageable) {
        Page<Chat> chats = chatRepository.findByChatRoomRoomNo(roomNo, pageable);
        Page<ChatResponse> chatResponse =  chats.map(chat -> new ChatResponse(
                chat.getChatNo(),
                chat.getChatRoom().getRoomNo(),
                chat.getUser().getUserNo(),
                chat.getChatTxt(),
                chat.getCreatedAt(),
                chat.getUpdateAt()
        ));
        return ChatApiResponse.success(chatResponse, "채팅 내역을 성공적으로 불러왔습니다.");
    }

    /* 채팅 내 메시지 검색 GET /chat/rooms/{roomNo}/search */
    @Transactional(readOnly = true)
    public ChatApiResponse<Page<ChatResponse>> searchChatsByRoomNo(Long roomNo, String searchTxt, Pageable pageable) {
        Page<Chat> searchChats = chatRepository.findByChatRoomRoomNoAndChatTxtChatTxtContaining(roomNo, searchTxt, pageable);
        Page<ChatResponse> chatResponses = searchChats.map(chat -> new ChatResponse(
                chat.getChatNo(),
                chat.getChatRoom().getRoomNo(),
                chat.getUser().getUserNo(),
                chat.getChatTxt(),
                chat.getCreatedAt(),
                chat.getUpdateAt()
        ));
        return ChatApiResponse.success(chatResponses, "검색 결과입니다.");
    }
}
