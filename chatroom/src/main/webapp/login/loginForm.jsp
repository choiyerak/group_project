<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./auth.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="loginProc.jsp" method="post">
	아이디 : <input type="text" name="id">
	<br>
	비밀번호 : <input type="password" name="passwd">
	<br>
	<input type="submit" value="로그인">
	<button formaction="./logout.jsp">로그아웃</button>
	</form>
	<%=s_id %>
</body>
</html>