<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	//response.sendRedirect("login/loginPage"); 
	request.getRequestDispatcher("/login/loginPage").forward(request,response);
%>
