package com.nowwater.board.springwebservice.chatting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker// used to enable our WebSocket server
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // STOMP : Simple Text Oriented Messaging Protocol
    // 데이터 교환의 형식과 규칙을 정의하는 메세지 프로토콜
    // 사용 이유 : WebSocket은 통신 프로토콜 일뿐, 특정 주제를 구독한 사용자에게만 메시지를 보내는 방법 또는
    // 특정 사용자에게 메시지를 보내는 방법과 같은 내용은 정의하지 않는다. 따라서 이러한 기능을 위해서는 STOMP가 필요
    // 즉, 특정 사용자간에 메세지 교환 방법을 정의하기 위해서 사용..

    // 클라이언트가 웹 소켓 서버에 연결하는데 사용할 웹 소켓 엔드 포인트를 등록
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
        // withSockJS : 웹 소켓을 지원하지 않는 브라우저에 폴백 옵션을 활성화하는데 사용
        // Fallback : 어떤 기능이 약해지거나 제대로 동작하지 않을 때, 이에 대처하는 기능 또는 동작.
    }


    // 인 메모리 메시지 브로커
    // 한 클라이언트에서 다른 클라이언트로 메시지를 라우팅 하는 데 사용될 메시지 브로커를 구성
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // "/app" 시작되는 메시지가 message-handling methods으로 라우팅 되어야 한다는 것을 명시
        registry.setApplicationDestinationPrefixes("/app");

        // "/topic" 시작되는 메시지가 메시지 브로커로 라우팅 되도록 정의한다.
        registry.enableSimpleBroker("/topic");

        // 메시지 브로커는 특정 주제를 구독 한 연결된 모든 클라이언트에게 메시지를 broadcast 한다.
        // 브로드캐스팅 : 송신 호스트가 전송한 데이터가 네트워크에 연결된 모든 호스트에 전송되는 방식


    }
} 
