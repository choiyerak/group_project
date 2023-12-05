<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cdao" class="chatroom.ChatroomDAO" scope="page"></jsp:useBean>  
<jsp:useBean id="cdto" class="chatroom.ChatroomDTO" scope="page"></jsp:useBean>
<%@include file="auth.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");	

	int chatroomno = Integer.parseInt(request.getParameter("crno"));
	String chatroommemnick = request.getParameter("memnick");
	cdto.setChatroomno(chatroomno);
	
	cdto = cdao.checkRoomInfo(cdto);
	// out.print(cdto);
	cdto.setChatroommem(s_id);
	cdto.setChatroommemnick(chatroommemnick);
	out.print(cdto);
	int cnt = cdao.chatroomJoin(cdto);
	
	if(cnt == 0){
		out.print("<script>");
		out.print("alert('채팅방 가입 실패');");
		out.print("location.href='../chatroom/chatroomJoin.jsp?crno=" + chatroomno + "'");
		out.print("</script>");
	} else {
		out.print("<script>");
		out.print("alert('채팅방 추가 실패');");
		out.print("location.href='../chatroom/chatroomList.jsp';");
		out.print("</script>");
	}
%>
</body>
</html>