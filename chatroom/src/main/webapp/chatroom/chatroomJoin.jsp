<%@page import="chatroom.ChatroomDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cdao" class="chatroom.ChatroomDAO" scope="page"></jsp:useBean>  
<jsp:useBean id="cdto" class="chatroom.ChatroomDTO" scope="page"></jsp:useBean>
<%@include file="auth.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅방 가입하기</title>
</head>
<body>
<%
	int chatroomno = Integer.parseInt(request.getParameter("crno"));
	cdto.setChatroomno(chatroomno);
	cdto.setChatroommem(s_id);

	// 가입 여부 확인 하기 
	int cnt = cdao.checkMem(cdto);
	
	// 결과 확인 
	// out.print(cnt);
	
	if(cnt != 0){
		out.print("<script>");
		out.print("alert('이미 가입한 채팅방입니다.');");
		out.print("location.href='../chatroom/chatroomList.jsp';");
		out.print("</script>");
	}else {
	
	// 방정보 불러오기 
	cdto = cdao.checkRoomInfo(cdto);
	
	// cdto 정보 확인 
	//out.print(cdto);
%>
	<table>
		<tr>
			<th>방이름</th>
			<td><%=cdto.getChatroomname()%></td>
		</tr>
		<tr>
			<th>방장</th>
			<td><%=cdto.getChatroomownernick()%></td>
		</tr>
	</table>
	<button onclick="return checkJoin(<%=cdto.getChatroomno()%>);">가입하기</button>
<%}%> <!-- if ends -->
<script>
	function checkJoin(chatroomno){
		// 연결 확인 
		// alert("hi");
		
		if(confirm("가입하시겠습니까?")){
			let memnick = prompt("닉네임을 적어주세요");
			location.href = 'chatroomJoinProc.jsp?crno=' + chatroomno + '&memnick=' + memnick;
		} // if ends 
		
		return false;
	} // checkJoin() ends 
</script>
</body>
</html>