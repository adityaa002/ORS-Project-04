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
		UserBean user = (UserBean) session.getAttribute("user");
	%>

	<%
		if (user != null) {
	%>
	<h3>
		Hi,
		<%=user.getFirstName()%>
		(<%=session.getAttribute("role")%>)
	</h3>
	<a href="<%=ORSView.USER_CTL%>">Add User</a> |
	<a href="<%=ORSView.USER_LIST_CTL%>">User List</a> |

	<a href="<%=ORSView.ROLE_CTL%>">Add Role</a> |
	<a href="<%=ORSView.ROLE_LIST_CTL%>">Role List</a> |
	
	<a href="<%=ORSView.COLLEGE_CTL%>">Add College</a> |
	<a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</a> |
	
	<a href="<%=ORSView.STUDENT_CTL%>">Add Student</a> |
	<a href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</a> |
	
	<a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a> |
	<a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</a> |
	
	<a href="<%=ORSView.COURSE_CTL%>">Add Course</a> |
	<a href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a> |
	
	
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