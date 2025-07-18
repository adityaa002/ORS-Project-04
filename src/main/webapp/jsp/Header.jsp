<%@page import="in.co.rays.controller.LoginCtl"%>
<%@page import="in.co.rays.bean.UserBean"%>
<%@page import="in.co.rays.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>

	<%
		UserBean userBean = (UserBean) session.getAttribute("user");
	%>

	<%
		if (userBean != null) {
	%>
	<h3>
		Hi,
		<%=userBean.getFirstName()%>
		(<%=session.getAttribute("role")%>)
	</h3>
	<a href="<%=ORSView.USER_CTL%>">Add User</a> |
	<a href="#">User List</a> |
	<a href="LoginCtl?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a>


	<%
		} else {
	%>

	<h3>Hey Guest,</h3>
	<a href="<%=ORSView.WELCOME_CTL%>">Welcome</a> |
	<a href="<%=ORSView.LOGIN_CTL%>">Login</a>

	<%
		}
	%>
</body>
</html>