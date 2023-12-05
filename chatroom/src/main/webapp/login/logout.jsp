<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout</title>
</head>
<body>
<% 
	session.removeAttribute("s_id");
	session.removeAttribute("s_passwd");
	session.removeAttribute("s_ulevel");
%>
<script>
	location.href="./loginForm.jsp";
</script>
</body>
</html>