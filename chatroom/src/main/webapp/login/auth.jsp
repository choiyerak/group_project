<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- auth.jsp --%>

<% 
	String s_id = "";
	String s_passwd = "";
	String s_ulevel = "";
	
	if(
			session.getAttribute("s_id") == null
		||	session.getAttribute("s_passwd") == null
	  ){
		s_id = "guest";
		s_passwd = "guest";
		s_ulevel = "guest";
	} else {
		s_id = (String)session.getAttribute("s_id");
		s_passwd = (String)session.getAttribute("s_passwd");
	}
%>