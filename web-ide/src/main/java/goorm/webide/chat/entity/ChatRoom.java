package goorm.webide.chat.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * packageName    : goorm.webide.chat.entity
 * fileName       : ChatRoom
 * author         : won
 * date           : 2024/04/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/14        won       최초 생성
 */

@Entity
@Getter
@NoArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomNo;

    @Column(nullable = false)
    private String roomName;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<ChatUser> chatUsers;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Chat> chats;

    @Builder
    public ChatRoom(String roomName, LocalDateTime createdAt, Set<ChatUser> chatUsers, List<Chat> chats) {
        this.roomName = roomName;
        this.createdAt = createdAt;
        this.chatUsers = chatUsers != null ? chatUsers : new HashSet<>();
        this.chats = chats != null ? chats : new ArrayList<>();
    }
}

