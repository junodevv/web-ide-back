package goorm.webide.chat.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.Collections;
import java.util.List;

/**
 * packageName    : goorm.webide.chat.controller
 * fileName       : WebSocketStompClientConfig
 * author         : won
 * date           : 2024/04/18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/18        won       최초 생성
 */

@Profile("test")
@Configuration
public class WebSocketStompClientConfig {
    @Bean
    public WebSocketStompClient stompClient() {
        return new WebSocketStompClient(new SockJsClient(createTransportClient()));
    }

    private List<Transport> createTransportClient() {
        return Collections.singletonList(new WebSocketTransport(new StandardWebSocketClient()));
    }
}
