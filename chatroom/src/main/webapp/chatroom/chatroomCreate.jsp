<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="auth.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅방 만들기</title>
</head>
<body>
	<h3>채팅방 만들기</h3>
	<!-- 채팅방 만들기 입력창 : 필요 정보-->
	<form action="chatroomCreateProc.jsp">
		<table>
			<tr>
				<th>방장</th>
				<td>
					<input type="text" value="<%=s_id%>" name="chatroomowner" disabled>
					<input type="hidden" value="<%=s_id%>" name="chatroomowner">
				</td>
			</tr>
			<tr>
				<th>방이름</th>
				<td><input type="text" name="chatroomname" placeholder="20자 이내로 적어주세요" max-length="20"></td>
			</tr>
		</table>
		<!-- 유효성 검사 필요: 방이름이 안써져 있는 경우 -->
		<button>방 만들기</button>
	</form>
	<br>
	<button onclick="location.href='chatroomList.jsp'">채팅방 목록으로</button>
</body>
</html>