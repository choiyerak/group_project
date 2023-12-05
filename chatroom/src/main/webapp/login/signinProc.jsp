<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="udao" class="user.UserDAO" scope="page"></jsp:useBean>  
<jsp:useBean id="udto" class="user.UserDTO" scope="page"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	String nick = request.getParameter("nick");
	
	udto.setId(id);
	udto.setPasswd(passwd);
	udto.setNick(nick);
	
	int cnt = udao.create(udto);
	
	if(cnt == 0){
		out.print("<script>");
		out.print("alert('회원 가입 실패');");
		out.print("location.href='./signinForm.jsp';");
		out.print("</script>");
	} else {
		out.print("<script>");
		out.print("alert('회원 가입 성공');");
		out.print("location.href='./loginForm.jsp';");
		out.print("</script>");
	}
%>
</body>
</html>