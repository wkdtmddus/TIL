package com.ssafy.ios.lineup.backend.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ssafy.ios.lineup.backend.application.service.chat.redis.RedisSubscriber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
//@RequiredArgsConstructor
public class RedisConfig {

    @PostConstruct
    public void init() {
        // SecurityContextHolder의 모드를 InheritableThreadLocal로 설정
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        lettuceConnectionFactory.setHostName(redisHost);
        lettuceConnectionFactory.setPort(redisPort);
        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Key Serializer 설정
        template.setKeySerializer(new StringRedisSerializer());

        // ObjectMapper에 JavaTimeModule 추가
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // ISO 형식으로 날짜를 직렬화

        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        serializer.setObjectMapper(objectMapper);


        // Value Serializer 설정 (String 직렬화)
//        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        template.setValueSerializer(serializer);

        return template;
    }

    // redis에 publish된 메세지 처리를 위한 리스너 설정
    @Bean
    public RedisMessageListenerContainer redisMessageListener(
            MessageListenerAdapter listenerAdapterChatMessage
    ) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory());
        container.addMessageListener(listenerAdapterChatMessage, new PatternTopic("chatRoom_*"));

        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapterChatMessage(RedisSubscriber subscriber) {
        return new MessageListenerAdapter(subscriber, "sendMessage");
    }

    /* SimpMessageSendingOperations (WebSocket과 STOMP)를 활용해서 해보려고 주석 */
//    @Bean
//    public RedisMessageListenerContainer redisMessageListenerRoomList(
//            MessageListenerAdapter listenerAdapterChatRoomList
//    ) {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(redisConnectionFactory());
//        container.addMessageListener(listenerAdapterChatRoomList, new PatternTopic("chatRoomList"));
//
//        return container;
//    }
//
//    @Bean
//    public MessageListenerAdapter listenerAdapterChatRoomList(RedisSubscriber subscriber) {
//        return new MessageListenerAdapter(subscriber, "sendRoomList");
//    }
}
