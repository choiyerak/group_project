<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="signinProc.jsp" method="post">
		닉네임 <input type="text" name="nick">
		<br>
		아이디 <input type="text" name="id">
		<br>
		비밀번호 <input type="password" name="passwd">
		<br>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>