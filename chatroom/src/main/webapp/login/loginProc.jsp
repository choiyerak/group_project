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
	
	udto.setId(id);
	udto.setPasswd(passwd);
	
	int cnt = udao.login(udto);
	
	if(cnt == 0){
		out.print("<script>");
		out.print("alert('로그인 실패');");
		out.print("location.href='./loginForm.jsp';");
		out.print("</script>");
	}else {
		session.setAttribute("s_id", id);
		session.setAttribute("s_passwd", passwd);
		out.print("<script>");
		out.print("alert('로그인 성공');");
		out.print("location.href='../chatroom/chatroomList.jsp';");
		out.print("</script>");
		
		
	}
%>
</body>
</html>