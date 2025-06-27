<%@page import="in.co.rays.Proj04.controller.LoginCtl"%>
<%@page import="in.co.rays.Proj04.util.ServletUtility"%>
<%@page import="in.co.rays.Proj04.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>



<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="bean" class="in.co.rays.Proj04.bean.UserBean"
		scope="request"></jsp:useBean>

	<%@ include file="Header.jsp"%>

	<div align="center">
		<h1>Login</h1>
		<form action="<%=ORSView.LOGIN_CTL%>" method="post">

			<table>
				<tr>
					<th>LoginId <span style="color: red">*</span> :
					</th>
					<td><input type="text" name="login"
						placeholder="Enter valid Email-Id" size="26"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"><font
						color="red"><%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>

				<tr>
					<th>Password <span style="color: red">*</span> :
					</th>
					<td><input type="password" name="password"
						placeholder="Enter Password" size="26"
						value="<%=DataUtility.getStringData(bean.getPassword())%>"><font
						color="red"><%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
			</table>

			<table>
				<tr>
					<input type="submit" name="operation"
						value="<%=LoginCtl.OP_SIGN_IN%>">
				</tr>
			</table>

		</form>

	</div>

	<%@ include file="Footer.jsp"%>

</body>
</html>