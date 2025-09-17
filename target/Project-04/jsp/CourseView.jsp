<%@page import="in.co.rays.controller.CourseCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Course</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<jsp:useBean id="bean" class="in.co.rays.bean.CourseBean"
		scope="request"></jsp:useBean>

	<%
		HashMap<String, String> map = (HashMap<String, String>) request.getAttribute("map");
	%>

	<%@include file="Header.jsp"%>

	<form action="<%=ORSView.COURSE_CTL%>" method="post">
		<div align="center">
			<h1 align="center" style="margin-bottom: -15; color: navy">
				<%
					if (bean.getId() > 0 && bean != null) {
				%>
				Update
				<%
					} else {
				%>
				Add
				<%
					}
				%>
				Course
			</h1>
			<div>

				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h3>
				<h3>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
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

			<table>
				<tr>
					<th align="left">Name<span style="color: red">*</span></th>
					<td><input type="text" name="name"
						placeholder="Enter Course Name"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td><font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Duration<span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("duration", String.valueOf(bean.getDuration()), map)%></td>
					<td><font color="red"><%=ServletUtility.getErrorMessage("duration", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Description<span style="color: red">*</span></th>
					<td><textarea name="description"
							placeholder="Enter Short description" rows="3" cols="22"><%=DataUtility.getStringData(bean.getDescription())%></textarea>
					</td>
					<td><font color="red"><%=ServletUtility.getErrorMessage("description", request)%></font></td>
				</tr>

				<tr>
					<th></th>
					<td colspan="2" align="left">
						<%
							if (bean != null && bean.getId() > 0) {
						%> <input type="submit" name="operation"
						value="<%=CourseCtl.OP_UPDATE%>"> <input type="submit"
						name="operation" value="<%=CourseCtl.OP_CANCEL%>"> <%
 	} else {
 %> <input type="submit" name="operation" value="<%=CourseCtl.OP_SAVE%>">
						<input type="submit" name="operation"
						value="<%=CourseCtl.OP_RESET%>"> <%
 	}
 %>
					</td>
				</tr>
			</table>
		</div>
	</form>
	<%@include file="Footer.jsp" %>
</body>
</html>