package com.ssafy.ios.lineup.backend.domain.repository.chat;

import com.ssafy.ios.lineup.backend.domain.entity.ChatRoom;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import java.util.List;

public interface ChatRoomCustomRepository {

    List<ChatRoom> findByRecruitId(Long recruitId);

    List<ChatRoom> findByUser(User user);
}
