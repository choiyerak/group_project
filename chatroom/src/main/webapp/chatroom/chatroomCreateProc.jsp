<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cdao" class="chatroom.ChatroomDAO" scope="page"></jsp:useBean>  
<jsp:useBean id="cdto" class="chatroom.ChatroomDTO" scope="page"></jsp:useBean>
<%@include file="auth.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅방 만들기</title>
</head>
<body>
	<h3>채팅방 프로세스</h3>
<%	
	request.setCharacterEncoding("utf-8");
	String chatroomowner= request.getParameter("chatroomowner");	
	String chatroomname = request.getParameter("chatroomname");
	
	cdto.setChatroomowner(chatroomowner);
	cdto.setChatroomname(chatroomname);
	
	int cnt = cdao.create(cdto); 
	
	if(cnt == 0){
		out.print("<script>");
		out.print("alert('채팅방 추가 실패');");
		out.print("location.href='../chatroom/chatroomCreate.jsp';");
		out.print("</script>");
	} else {
		out.print("<script>");
		out.print("alert('채팅방 추가 성공');");
		out.print("location.href='../chatroom/chatroomList.jsp';");
		out.print("</script>");
	} // if ends 채팅방 추가 여부 확인 
	
%>	
</body>
</html>