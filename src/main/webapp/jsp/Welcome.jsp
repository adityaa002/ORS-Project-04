<%@page import="in.co.rays.bean.RoleBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<form action="<%=ORSView.WELCOME_CTL%>">

		<%@include file="Header.jsp"%>
 		<br> <br> <br> <br> <br> <br> <br>
		<br>
		<div align="center">


			<h1>Welcome to ORS</h1>
			<%
				UserBean beanUserBean = (UserBean) session.getAttribute("user");
				if (beanUserBean != null) {
					if (beanUserBean.getRoleId() == RoleBean.STUDENT) {
			%>
			<h2>
				<a href="<%=ORSView.GET_MARKSHEET_CTL%>">Click here to view your marksheet</a>
			</h2>
			<%
				}
				}
			%>


		</div>
	</form>
	
	<%@include file="Footer.jsp" %>
</body>
</html>