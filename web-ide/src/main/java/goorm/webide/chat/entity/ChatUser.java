package goorm.webide.chat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ChatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatUserNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomNo", nullable = false)
    private ChatRoom chatRoom;
}

