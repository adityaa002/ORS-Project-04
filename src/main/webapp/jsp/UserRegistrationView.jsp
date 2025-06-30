<%@page import="in.co.rays.Proj04.controller.UserRegistrationCtl"%>
<%@page import="in.co.rays.Proj04.util.ServletUtility"%>
<%@page import="in.co.rays.Proj04.util.DataUtility"%>
<%@page import="in.co.rays.Proj04.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
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


	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">
		<h1 align="center">User Registration</h1>

		<div align="center">

			<h3 style="color: green"><%=ServletUtility.getSuccessMessage(request)%></h3>
			<h3 style="color: red"><%=ServletUtility.getErrorMessage(request)%></h3>

			<table>
				<tr>
					<th>First Name<span style="color: red">*</span> :
					</th>
					<td><input type="text" name="firstName"
						placeholder="Enter First Name" size="26"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"><font
						color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>

				<tr>
					<th style="padding: 3px"></th>
					<td></td>
				</tr>

				<tr>
					<th>Last Name <span style="color: red">*</span> :
					</th>
					<td><input type="text" name="lastName"
						placeholder="Enter last Name" size="26"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"><font
						color="red"><%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>

				<tr>
					<th style="padding: 3px"></th>
					<td></td>
				</tr>

				<tr>
					<th>LoginId <span style="color: red">*</span> :
					</th>
					<td><input type="text" name="login"
						placeholder="Enter valid Email-Id" size="26"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"><font
						color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
				</tr>

				<tr>
					<th style="padding: 3px"></th>
					<td></td>
				</tr>

				<tr>
					<th>Gender <span style="color: red">*</span> :
					</th>
					<td>
						<%
							HashMap map = new HashMap();

							map.put("Male", "Male");
							map.put("Female", "Female");
							map.put("Other", "Other");

							String htmlList = HTMLUtility.getList("gender", bean.getGender(), map);
						%> <%=htmlList%><font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font>
					</td>
				</tr>

				<tr>
					<th style="padding: 3px"></th>
					<td></td>
				</tr>

				<tr>
					<th>Date Of Birth <span style="color: red">*</span> :
					</th>
					<td><input type="date" name="dob" size="26"
						placeholder="Enter Dob "
						value="<%=DataUtility.getDateString(bean.getDob())%>"> <font
						color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font>
				</tr>

				<tr>
					<th style="padding: 3px"></th>
					<td></td>
				</tr>

				<tr>
					<th>Mobile No <span style="color: red">*</span> :
					</th>
					<td><input type="text" name="mobileNo"
						placeholder="Enter Mobile No" size="26" maxlength="10"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
						<font color="red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></font>
				</tr>

				<tr>
					<th style="padding: 3px"></th>
					<td></td>
				</tr>

				<tr>
					<th>Password <span style="color: red">*</span> :
					</th>
					<td><input type="password" name="password"
						placeholder="Enter Password" size="26"
						value="<%=DataUtility.getStringData(bean.getPassword())%>"><font
						color="red"><%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>


				<tr>
					<th style="padding: 3px"></th>
					<td></td>
				</tr>

				<tr>
					<th>Confirm Password <span style="color: red">*</span> :
					</th>
					<td><input type="password" name="confirmPassword"
						placeholder="Re-Enter password" size="26"
						value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>">
						<font color="red"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
				</tr>

				<tr>
					<th style="padding: 3px"></th>
					<td></td>
				</tr>

			</table>

			<table>
				<tr>
					<input type="submit" name="operation"
						value="<%=UserRegistrationCtl.OP_SIGN_UP%>">
				</tr>
			</table>

		</div>

	</form>
	<%@include file="Footer.jsp"%>

</body>
</html>