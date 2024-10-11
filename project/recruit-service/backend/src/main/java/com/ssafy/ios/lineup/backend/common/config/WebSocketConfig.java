package com.ssafy.ios.lineup.backend.common.config;

import com.ssafy.ios.lineup.backend.common.interceptor.JwtInterceptor;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${cors.allowedOrigins}")
    private String allowedOrigins;

    private final JwtInterceptor jwtInterceptor;

    @PostConstruct
    public void init() {
        // SecurityContextHolder의 모드를 InheritableThreadLocal로 설정
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub");
        config.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
//                .setAllowedOriginPatterns(allowedOrigins)
                .setAllowedOrigins(allowedOrigins)
                .addInterceptors(new HttpSessionHandshakeInterceptor()) // HTTP 세션 인증 정보 전달
                .withSockJS();

    }

    //     stompHandler가 websocket 앞단에서 작업을 할 수 있도록 함.
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        registration.interceptors(jwtInterceptor);
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(50 * 1024 * 1024); // 메세지 크기 제한 오류 방지(이 코드가 없으면 byte code를 보낼때 소켓 연결이 끊길 수 있음)
    }


}
