package chatroom;

import java.io.IOException;
import java.io.StringReader;
import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;

@ServerEndpoint("/chat/{room}/{username}")
public class Client {
	
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(@PathParam("room")String room, Session session, @PathParam("username")String username) {
		
		// 해당 방에 사용자 추가
	    ChatRoom chatRoom = ChatRoomManager.getChatRoom(room);
	    chatRoom.addUser(session);
		
		// 연결이 열리면 클라이언트 세션을 추가함
		session.getUserProperties().put("room", room);
		session.getUserProperties().put("username", username);
		clients.add(session);
		
		
		String visit = username + "님이 입장했습니다.";
		String message = "{\"status\":\"visit\", \"message\":\"" + visit +"\"}";
		visit(room, message, session);
		
		// 웹 소켓 연결이 열렸을 때 로그 출력
	    System.out.println("WebSocket connection opened for room " + room);
	} // onOpen() ends 
	
	@OnClose
	public void onClose(@PathParam("room")String room, Session session, CloseReason closeReason) {
		
		 // 해당 방에서 사용자 제거
	    ChatRoom chatRoom = ChatRoomManager.getChatRoom(room);
	    chatRoom.removeUser(session);
		
		
		String username = (String) session.getUserProperties().get("username");
		
		String leave = username + "님이 퇴장했습니다.";
		String message = "{\"status\":\"leave\", \"message\":\"" + leave +"\"}";
		
		leave(room, session, message);
		
		// 연결이 닫히면 클라이언트 세션을 해당 채팅방에서 제거
		clients.remove(session);
		System.out.println("WebSocket connection removed for room " + room + username);
	} // onClose() ends 
	
	@OnError
	public void onError(Throwable error) {
		System.out.println(error);
	} // onError() ends 
	
	@OnMessage
	public void onMessage(@PathParam("room") String room, String message, Session session) throws IOException {
		// 현재 메시지를 보낸 클라이언트의 세션에서 세션 ID를 가져와서 저장. 이 ID는 클라이언트를 고유하게 식별하는 데 사용됨
		String senderClientId = session.getId();
	    
		String username = (String)session.getUserProperties().get("username");
		
		String messages = "{\"status\":\"input\", \"message\":\"" + message +"\", \"username\":\"" + username + "\"}";
		System.out.println(messages);
		// 클라이언트로부터 메시지를 받으면 해당 채팅방의 모든 클라이언트에게 메시지를 전송합니다.
		for(Session client : clients) {
			if(client.getUserProperties().get("room").equals(room) && !client.getId().equals(senderClientId)) {
				client.getBasicRemote().sendText(messages);
			} // if ends 
		} // for ends
		
		
	} // onMessage() ends 
	
	// 입장시 전체 사용자에게 메시지를 보내기
	 private void visit(@PathParam("room") String room, String message, Session senderSession) {
		 for(Session client : clients) {
			if(client.getUserProperties().get("room").equals(room)) {
				try {
	            	client.getBasicRemote().sendText(message);
	            } catch (IOException e) {
	                e.printStackTrace();
	            } // t-c ends
			} // if ends 
             
        } // for ends 
    } // visit() ends
	 
	// 메시지 입력시 자신을 제외한 사용자에게 메시지 보내기 
	private void leave(@PathParam("room") String room, Session senderSession, String message) {
		String username = (String) senderSession.getUserProperties().get("username");
		for(Session client : clients) {
			if(client != senderSession && client.getUserProperties().get("room").equals(room)) {
				try {
	            	client.getBasicRemote().sendText(message);
	            } catch (IOException e) {
	                e.printStackTrace();
	            } // t-c ends
			} // if ends
        } // for ends
	} // leave() ends 
	
	// 특정 방의 현재 접속 중인 사용자 수 가져오기
	public int getOnlineUsersCount(@PathParam("room") String room) {
	    ChatRoom chatRoom = ChatRoomManager.getChatRoom(room);
	    return chatRoom.getOnlineUsersCount();
	} // getOnlineUsersCount() ends 
} // class ends 
