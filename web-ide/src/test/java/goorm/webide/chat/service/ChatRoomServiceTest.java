package goorm.webide.chat.service;

import goorm.webide.chat.dto.ChatRoomRequest;
import goorm.webide.chat.dto.ChatRoomResponse;
import goorm.webide.chat.entity.ChatRoom;
import goorm.webide.chat.entity.ChatUser;
import goorm.webide.chat.repository.ChatRoomRepository;
import goorm.webide.chat.repository.ChatUserRepository;
import goorm.webide.user.entity.User;
import goorm.webide.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * packageName    : goorm.webide.chat.service
 * fileName       : ChatRoomServiceTest
 * author         : won
 * date           : 2024/04/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/17        won       최초 생성
 */

@SpringBootTest
class ChatRoomServiceTest {
    @Autowired
    ChatRoomService chatRoomService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    ChatRoomRepository chatRoomRepository;
    @MockBean
    ChatUserRepository chatUserRepository;

    @BeforeEach
    public void beforeEach() {
        chatRoomService = new ChatRoomService(userRepository, chatRoomRepository, chatUserRepository);
    }

    /* 채팅방 생성 */
    @Test
    @Transactional
    public void createChatRoomTest() {
        // given
        ChatRoomRequest roomRequest = new ChatRoomRequest(1L, "test room");
        ChatRoom chatRoom = ChatRoom.builder()
                .roomName(roomRequest.getRoomName())
                .createdAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
        User user = Mockito.mock(User.class);

        when(chatRoomRepository.save(any(ChatRoom.class))).thenReturn(chatRoom);
        when(userRepository.findById(roomRequest.getUserNo())).thenReturn(Optional.of(user));

        // when
        ChatRoomResponse roomResponse = chatRoomService.createChatRoom(roomRequest);

        // then
        assertNotNull(roomResponse);
        assertEquals(roomRequest.getRoomName(), roomResponse.getRoomName());
        assertEquals(roomRequest.getUserNo(), roomResponse.getUserNo());
        verify(chatRoomRepository).save(any(ChatRoom.class));
        verify(userRepository).findById(roomRequest.getUserNo());
        verify(chatUserRepository).save(any(ChatUser.class));
    }

    /* 전체 채팅방 목록 조회 */
    @Test
    @Transactional
    public void findAllRoomsTest() {
        // given
        ChatRoomRequest roomRequest1 = new ChatRoomRequest(null, "room1");
        ChatRoom chatRoom1 = ChatRoom.builder()
                .roomName(roomRequest1.getRoomName())
                .createdAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
        ChatRoomRequest roomRequest2 = new ChatRoomRequest(null, "room2");
        ChatRoom chatRoom2 = ChatRoom.builder()
                .roomName(roomRequest2.getRoomName())
                .createdAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        List<ChatRoom> rooms = Arrays.asList(chatRoom1, chatRoom2);
        when(chatRoomRepository.findAll()).thenReturn(rooms);

        // when
        List<ChatRoomResponse> roomResponses = chatRoomService.findAllRooms();

        // then
        assertNotNull(roomResponses);
        assertEquals(2, roomResponses.size());
        assertEquals("room1", roomResponses.get(0).getRoomName());
        assertEquals("room2", roomResponses.get(1).getRoomName());
        verify(chatRoomRepository).findAll();
    }

    /* 회원별 채팅방 목록 조회 */
    @Test
    @Transactional
    public void findAllRoomsByUserIdTest() {
        // given
        Long userNo = 1L;
        ChatRoomRequest roomRequest1 = new ChatRoomRequest(userNo, "room1");
        ChatRoom chatRoom1 = ChatRoom.builder()
                .roomName(roomRequest1.getRoomName())
                .createdAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
        ChatRoomRequest roomRequest2 = new ChatRoomRequest(userNo, "room2");
        ChatRoom chatRoom2 = ChatRoom.builder()
                .roomName(roomRequest2.getRoomName())
                .createdAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
        User user = Mockito.mock(User.class);

        ChatUser chatUser1 = ChatUser.builder()
                .chatRoom(chatRoom1)
                .user(user)
                .build();
        ChatUser chatUser2 = ChatUser.builder()
                .chatRoom(chatRoom2)
                .user(user)
                .build();

        List<ChatUser> chatUsers = Arrays.asList(chatUser1, chatUser2);
        when(chatUserRepository.findByUserUserNo(userNo)).thenReturn(chatUsers);

        // when
        List<ChatRoomResponse> roomResponses = chatRoomService.findAllRoomsByUserId(userNo);

        // then
        assertNotNull(roomResponses);
        assertEquals(2, roomResponses.size());
        assertEquals("room1", roomResponses.get(0).getRoomName());
        assertEquals("room2", roomResponses.get(1).getRoomName());
        verify(chatUserRepository).findByUserUserNo(userNo);
    }
}