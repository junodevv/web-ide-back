package goorm.webide.chat.controller;

import goorm.webide.chat.dto.ChatApiResponse;
import goorm.webide.chat.dto.ChatResponse;
import goorm.webide.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/chat/rooms/{roomNo}")
public class ChatController {

    private final ChatService chatService;

    /* 채팅 불러오기 GET /chat/rooms/{roomNo} */
    @GetMapping
    public ResponseEntity<ChatApiResponse<Page<ChatResponse>>> getChatsByRoomNO(
            @PathVariable("roomNo") Long roomNo,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        ChatApiResponse<Page<ChatResponse>> chatResponses = chatService.getAllChatsByRoomNo(roomNo, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(chatResponses);
    }

    /* 채팅 내 메시지 검색 GET /chat/rooms/{roomNo}/search */
    @GetMapping("/search")
    public ResponseEntity<ChatApiResponse<Page<ChatResponse>>> getChatsByRoomNo(
            @PathVariable("roomNo") Long roomNo,
            @RequestParam("searchTxt") String searchTxt,
            Pageable pageable
    ) {
        ChatApiResponse<Page<ChatResponse>> chatResponses = chatService.searchChatsByRoomNo(roomNo, searchTxt, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(chatResponses);
    }
}
