package com.ssafy.ios.lineup.backend.domain.repository.chat;

import com.ssafy.ios.lineup.backend.domain.entity.Application;
import com.ssafy.ios.lineup.backend.domain.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>,
        ChatRoomCustomRepository {

    ChatRoom findByApplication(Application application);
}
