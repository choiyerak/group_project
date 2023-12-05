package chatroom;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.Session;

public class ChatRoom {
    private String roomId;
    private Set<Session> clients;

    // 생성자: ChatRoom 객체를 생성할 때 방의 아이디를 초기화하고 클라이언트 세션을 저장할 Set을 초기화합니다.
    public ChatRoom(String roomId) {
        this.roomId = roomId;
        this.clients = new HashSet<>();
    }

    // 사용자 추가: 특정 세션을 해당 방의 클라이언트 목록에 추가합니다.
    public void addUser(Session userSession) {
        clients.add(userSession);
    }

    // 사용자 제거: 특정 세션을 해당 방의 클라이언트 목록에서 제거합니다.
    public void removeUser(Session userSession) {
        clients.remove(userSession);
    }

    // 현재 접속 중인 사용자 수 가져오기
    public int getOnlineUsersCount() {
        return clients.size();
    }

    // 다른 방 관리에 필요한 메서드들 추가 가능...

    // Getter, Setter 등...
}

