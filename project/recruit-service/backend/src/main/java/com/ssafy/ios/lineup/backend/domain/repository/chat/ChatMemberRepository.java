package com.ssafy.ios.lineup.backend.domain.repository.chat;

import com.ssafy.ios.lineup.backend.domain.entity.ChatMember;
import com.ssafy.ios.lineup.backend.domain.entity.ChatRoom;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {

    Optional<ChatMember> findByChatRoomAndUser(ChatRoom chatRoom, User user);
}
