
<%@page import="in.co.rays.bean.StudentBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.controller.MarksheetCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Marksheet</title>
<link rel="icon" type="logo.png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png">

</head>
<body>
	<%@ include file="Header.jsp"%>
	<div align="center">
		<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">

			<jsp:useBean id="bean" class="in.co.rays.bean.MarksheetBean"
				scope="request"></jsp:useBean>

			<%
				List<StudentBean> stList = (List<StudentBean>) request.getAttribute("stList");
			%>

			<h1 align="center" style="margin-bottom: -15; color: navy">
				<%
					if (bean != null && bean.getId() > 0) {
				%>Update<%
					} else {
				%>Add<%
					}
				%>
				Marksheet
			</h1>

			<div style="height: 15px; margin-bottom: 12px">
				<H3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</H3>
				<H3>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</H3>
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
					<th align="left">Roll No<span style="color: red">*</span></th>
					<td><input type="text" name="rollNo" maxlength="5"
						placeholder="Enter Roll No."
						value="<%=DataUtility.getStringData(bean.getRollNo())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("rollNo", request)%></font>
					</td>
				</tr>
				<tr>
					<th align="left">Name<span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("studentId", String.valueOf(bean.getStudentId()), stList) %></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("studentId", request)%></font>
					</td>
				</tr> 
				<tr>
					<th align="left">Physics<span style="color: red">*</span></th>
					<td><input type="text" name="physics" maxlength="3"
						placeholder="Enter Physics Marks"
						value="<%=DataUtility.getStringData(bean.getPhysics())%>">
					</td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("physics", request)%></font>
					</td>
				</tr>
				<tr>
					<th align="left">Chemistry<span style="color: red">*</span></th>
					<td><input type="text" name="chemistry" maxlength="3"
						placeholder="Enter Chemistry Marks"
						value="<%=DataUtility.getStringData(bean.getChemistry())%>">
					</td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("chemistry", request)%></font>
					</td>
				</tr>
				<tr>
					<th align="left">Maths<span style="color: red">*</span></th>
					<td><input type="text" name="maths" maxlength="3"
						placeholder="Enter Maths Marks"
						value="<%=DataUtility.getStringData(bean.getMaths())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("maths", request)%></font>
					</td>
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
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=MarksheetCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=MarksheetCtl.OP_CANCEL%>">
					</td>
					<%
						} else {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=MarksheetCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=MarksheetCtl.OP_RESET%>">
					</td>
					<%
						}
					%>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>