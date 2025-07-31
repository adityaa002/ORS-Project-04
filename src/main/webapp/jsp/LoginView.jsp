<%@page import="in.co.rays.controller.UserRegistrationCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.controller.ORSView"%>
<%@page import="in.co.rays.controller.LoginCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
<link rel="icon" type="logo.png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png">

</head>
<body>

	<form action="<%=ORSView.LOGIN_CTL%>" method="post">

		<%@include file="Header.jsp"%>
		<jsp:useBean id="bean" class="in.co.rays.bean.UserBean"
			scope="request"></jsp:useBean>



		<div align="center">

			<div align="center">
				<h1 align="center" style="margin-bottom: -15; color: navy;">User
					Login</h1>
				<font color="green" size="5px"><%=ServletUtility.getSuccessMessage(request)%></font>
				<font color="red" size="5px"><%=ServletUtility.getErrorMessage(request)%></font>
			</div>
			<br>

			<table>
				<tr>
					<th align="left">Login Id:<span style="color: red">*</span></th>
					<td><input type="text" name="loginId"
						placeholder="enter login id"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("loginId", request)%></font></td>
					</td>

				</tr>
				<tr>
					<th align="left">Password:<span style="color: red">*</span></th>
					<td><input type="password" name="password"
						placeholder="enter password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
					</td>

				</tr>

				<tr>
					<th></th>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=LoginCtl.OP_SIGN_IN%>">&nbsp; <input
						type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_UP%>"></td>
				</tr>


			</table>
				<h3><a href="<%=ORSView.FORGET_PASSWORD_CTL%>">forget your password?</a></h3>


		</div>

	</form>

</body>
</html>