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
	<a href="MyProfileCtl"><b>My Profile</b></a>
	<b>|</b>
	<a href="ChangePasswordCtl"><b>Change Password</b></a>
	<b>|</b>
	<a href="GetMarksheetCtl"><b>Get Marksheet</b></a>
	<b>|</b>
	<a href="MarksheetMeritListCtl"><b>Marksheet Merit List</b></a>
	<b>|</b>
	<a href="UserCtl"><b>Add User</b></a>
	<b>|</b>
	<a href="UserListCtl"><b>User List</b></a>
	<b>|</b>
	<a href="RoleCtl"><b>Add Role</b></a>
	<b>|</b>
	<a href="RoleListCtl"><b>Role List</b></a>
	<b>|</b>
	<a href="CollegeCtl"><b>Add College</b></a>
	<b>|</b>
	<a href="CollegeListCtl"><b>College List</b></a>
	<b>|</b>
	<a href="StudentCtl"><b>Add Student</b></a>
	<b>|</b>
	<a href="StudentListCtl"><b>Student List</b></a>
	<b>|</b>
	<a href="MarksheetCtl"><b>Add Marksheet</b></a>
	<b>|</b>
	<a href="MarksheetListCtl"><b>Marksheet List</b></a>
	<b>|</b>
	<a href="CourseCtl"><b>Add Course</b></a>
	<b>|</b>
	<a href="CourseListCtl"><b>Course List</b></a>
	<b>|</b>
	<a href="SubjectCtl"><b>Add Subject</b></a>
	<b>|</b>
	<a href="SubjectListCtl"><b>Subject List</b></a>
	<b>|</b>
	<a href="TimetableCtl"><b>Add Timetable</b></a>
	<b>|</b>
	<a href="TimetableListCtl"><b>Timetable List</b></a>
	<b>|</b>
	<a href="FacultyCtl"><b>Add Faculty</b></a>
	<b>|</b>
	<a href="FacultyListCtl"><b>Faculty List</b></a>
	<b>|</b>
	<a href="LoginCtl?operation=<%=LoginCtl.OP_LOG_OUT%>"><b>Logout</b></a>

	<%
		} else {
	%>

	<h3>Hey Guest,</h3>
	<a href="<%=ORSView.WELCOME_CTL%>">Welcome</a> |
	<a href="<%=ORSView.LOGIN_CTL%>">Login</a>

	<%
		}
	%>
	<b>
	<hr>
	</b>
</body>
</html>