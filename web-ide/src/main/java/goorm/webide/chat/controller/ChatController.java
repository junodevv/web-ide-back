package goorm.webide.chat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import goorm.webide.chat.dto.ChatApiResponse;
import goorm.webide.chat.dto.ChatRequest;
import goorm.webide.chat.dto.ChatResponse;
import goorm.webide.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : goorm.webide.chat.controller
 * fileName       : ChatController
 * author         : won
 * date           : 2024/04/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/14        won       최초 생성
 */

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/sendChat/{roomNo}")
    @SendTo("/topic/chat/{roomNo}")
    public ChatApiResponse<ChatResponse> sendChat(
            @Payload ChatRequest chatRequest,
            @DestinationVariable("roomNo") Long roomNo
    ) throws JsonProcessingException {
        if (chatRequest.getChatTxt() == null || chatRequest.getChatTxt().trim().isEmpty()) {
            return ChatApiResponse.fail("채팅 메시지를 입력해야 합니다.");
        }
        ChatResponse chatResponse = chatService.saveChat(chatRequest, roomNo);
        return ChatApiResponse.success(chatResponse, "채팅이 성공적으로 전송되었습니다.");
    }
}
