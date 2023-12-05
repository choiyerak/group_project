<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cdao" class="chatroom.ChatroomDAO" scope="page"></jsp:useBean>  
<jsp:useBean id="cdto" class="chatroom.ChatroomDTO" scope="page"></jsp:useBean>
<%@include file="auth.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pop up</title>
</head>
<body>
<%
	int chatroomno = Integer.parseInt(request.getParameter("crno"));
	String username = request.getParameter("username");
	String message = request.getParameter("message");
%>
</body>
<script src="../js/jquery-3.7.1.min.js"></script>
<!-- 이부분 공부 - 정리할 것!! -->
<script src="../js/popup.js" data-chatroomno="<%=chatroomno%>" data-s_id="<%=s_id%>" data-username="<%=username%>" data-message="<%=message%>"></script>
</html>