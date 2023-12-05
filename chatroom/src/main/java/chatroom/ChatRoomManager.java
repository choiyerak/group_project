package chatroom;

import java.util.HashMap;
import java.util.Map;

public class ChatRoomManager {
    private static Map<String, ChatRoom> chatRooms = new HashMap<>();

    // 특정 방의 ChatRoom 객체를 반환합니다. 없으면 새로 생성하여 반환합니다.
    public static ChatRoom getChatRoom(String roomId) {
        return chatRooms.computeIfAbsent(roomId, ChatRoom::new);
    }

    // 다른 방 관리에 필요한 메서드들 추가 가능...

    // 현재 생성된 모든 채팅방 목록을 반환합니다.
    public static Map<String, ChatRoom> getChatRooms() {
        return chatRooms;
    }
}
