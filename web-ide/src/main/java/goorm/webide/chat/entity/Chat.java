package goorm.webide.chat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatNo;

    @Column(columnDefinition = "json")
    private String chatTxt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomNo", nullable = false)
    private ChatRoom chatRoom;
}