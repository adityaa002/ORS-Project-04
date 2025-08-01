<%@page import="in.co.rays.controller.CollegeCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>College View</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>

	<form action="<%=ORSView.COLLEGE_CTL%>" method="post">
	<%@include file="Header.jsp"%>
	<jsp:useBean id="bean" class="in.co.rays.bean.CollegeBean"
		scope="request"></jsp:useBean>

		<div align="center">
			<h1 align="center" style="color: navy">
				<%
					if (bean != null && bean.getId() > 0) {
				%>
				Update
				<%
					} else {
				%>
				Add
				<%
					}
				%>
				College
			</h1>

			<div align="center">
				<h3>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h3>

			</div>

			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table style="align-content: center;">

				<tr>
					<th align="left">Name:<span style="color: red">*</span></th>
					<td><input type="text" name="name"
						placeholder="Enter College Name"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td><font style="position: fixed; color: red"><%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Address:<span style="color: red">*</span></th>
					<td><input type="text" name="address"
						placeholder="Enter Address"
						value="<%=DataUtility.getStringData(bean.getAddress())%>"></td>
					<td><font style="position: fixed; color: red"><%=ServletUtility.getErrorMessage("address", request)%></font></td>
				</tr>
				<tr>
					<th align="left">State:<span style="color: red">*</span></th>
					<td><input type="text" name="state" placeholder="Enter State"
						value="<%=DataUtility.getStringData(bean.getState())%>"></td>
					<td><font style="position: fixed; color: red"><%=ServletUtility.getErrorMessage("state", request)%></font></td>
				</tr>
				<tr>
					<th align="left">City:<span style="color: red">*</span></th>
					<td><input type="text" name="city" placeholder="Enter City"
						value="<%=DataUtility.getStringData(bean.getCity())%>"></td>
					<td><font style="position: fixed; color: red"><%=ServletUtility.getErrorMessage("city", request)%></td>
				</tr>
				<tr>
					<th align="left">Phone No:<span style="color: red">*</span></th>
					<td><input type="text" name="phoneNo"
						placeholder="Enter Phone No"
						value="<%=DataUtility.getStringData(bean.getPhoneNo())%>"></td>
					<td><font style="position: fixed; color: red"><%=ServletUtility.getErrorMessage("phoneNo", request)%></font></td>
				</tr>

				<tr>
					<th></th>
					<td></td>
				</tr>

				<tr>
					<th></th>

					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit" name="operation"
						value="<%=CollegeCtl.OP_UPDATE%>">
					<input type="submit" name="operation"
						value="<%=CollegeCtl.OP_CANCEL%>"></td>

					<%
						} else {
					%>
					
					<td align="left" colspan="2"><input type="submit" name="operation"
						value="<%=CollegeCtl.OP_SAVE%>">
					<input type="submit" name="operation"
						value="<%=CollegeCtl.OP_RESET%>"></td>
					
					
					<%
						}
					%>
				
			</table>

		</div>
	</form>


</body>
</html>