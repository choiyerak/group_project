<%@page import="chatroom.ChatroomDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="client" class="chatroom.Client" scope="page"></jsp:useBean>
<jsp:useBean id="cdao" class="chatroom.ChatroomDAO" scope="page"></jsp:useBean>  
<jsp:useBean id="cdto" class="chatroom.ChatroomDTO" scope="page"></jsp:useBean>
<%@include file="auth.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅방 목록</title>
</head>
<body>
	<h3>채팅방 목록</h3>
	<%if(s_id.equals("guest")){%>
	<script>
		location.href = '../login/loginForm.jsp';
	</script>
	<%}%>
	<%=s_id%>
	<table>
		<tr>
			<th>방이름</th>
			<th>방장 이름</th>
			<th>접속자수/가입자수</th>
			<th>입장</th>
		</tr>
<%
ArrayList<chatroom.ChatroomDTO> clist = null;
	clist = cdao.chatroomList();
	if(clist == null){
%>
		<tr>
			<td colspan="3">채팅방이 없습니다.</td>
		</tr>
<%
} else{
		for(int i=0;i<clist.size();i++){
	cdto = new chatroom.ChatroomDTO();
	cdto = clist.get(i);
%>
		<tr>
			<td><%=cdto.getChatroomname()%></td>
			<td><%=cdto.getChatroomownernick()%></td>
			<td>
				<%=client.getOnlineUsersCount(Integer.toString(cdto.getChatroomno()))%>
				/
				<%=cdao.getSigninUsersCount(cdto.getChatroomno())%> 
			</td>
			<td>
				<%
				chatroom.ChatroomDTO dto = new chatroom.ChatroomDTO();
				dto.setChatroomno(cdto.getChatroomno());
				dto.setChatroommem(s_id);
				if(cdao.checkMem(dto) != 0){
		%>
				<button onclick="openChatroom(<%=cdto.getChatroomno()%>)">입장하기</button>		
				<%-- <button onclick="location.href='chatroomEnter.jsp?crno=<%=cdto.getChatroomno()%>'">입장하기</button> --%>		
			<%} else { %>
				<button onclick="location.href='chatroomJoin.jsp?crno=<%=cdto.getChatroomno()%>'">가입하기</button>
			<%} // if ends 채팅창 가입 여부 확인 
		%>
			</td>				
		</tr>
<%  	}
	
	}// if ends 목록 존재 여부 확인
%> 
	</table>
	<button onclick="location.href='chatroomCreate.jsp'">채팅방 만들기</button>
	<br>
	<button onclick="location.href='../login/loginForm.jsp'">로그인</button>
	<script src="../js/chatroomList.js"></script>
</body>
</html>