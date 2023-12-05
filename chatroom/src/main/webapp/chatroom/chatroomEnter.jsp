<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cdao" class="chatroom.ChatroomDAO" scope="page"></jsp:useBean>  
<jsp:useBean id="cdto" class="chatroom.ChatroomDTO" scope="page"></jsp:useBean>
<%@include file="auth.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅방 입장</title>
<link rel="stylesheet" href="../css/chatroom.css">
</head>
<body>
<%
	int chatroomno = Integer.parseInt(request.getParameter("crno"));
%>
<div id="chatBox"></div>
<input type="text" id="messageInput" placeholder="메시지를 입력해주세요">
<button class="sendBtn" onclick="sendMessage()">전송</button>
</body>
<script src="../js/jquery-3.7.1.min.js"></script>
<!-- 이부분 공부 - 정리할 것!! -->
<script src="../js/chatroom.js" data-chatroomno="<%=chatroomno%>" data-s_id="<%=s_id%>"></script>
</html>