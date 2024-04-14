package goorm.webide.chat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomNo;

    @Column(unique = true)
    private String roomName;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<ChatUser> chatUsers;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Chat> chats;
}

