<%@page import="in.co.rays.controller.RoleCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Role View</title>
<link rel="icon" type="logo.png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png">

</head>
<body>
	<%@include file="Header.jsp"%>

	<jsp:useBean class="in.co.rays.bean.RoleBean" id="bean" scope="request"></jsp:useBean>

	<form action="<%=ORSView.ROLE_CTL%>" method="post">

		<div align="center">
			<h1 align="center" style="margin-bottom: -15; color: navy">
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
				Role
			</h1>
			<div style="height: 15px; margin-bottom: 12px">
				<h3 align="center">
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%>
					</font>
				</h3>
				<h3 align="center">
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



			<table>
				<tr>
					<th align="left">Role:<span style="color: red">*</span></th>
					<td align="center"><input type="text" name="roleName"
						placeholder="Enter Role Name"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("roleName", request)%>
					</font></td>
				</tr>

				<tr>
					<th align="left">Description<span style="color: red">*</span></th>
					<td align="center"><textarea
							style="width: 173px; resize: none;" name="description" rows="3"
							placeholder="Enter Short description">
                          <%=DataUtility.getStringData(bean.getDescription()).trim()%>
                        </textarea></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("description", request)%>
					</font></td>
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
						name="operation" value="<%=RoleCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=RoleCtl.OP_CANCEL%>">
					</td>
					<%
						} else {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=RoleCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=RoleCtl.OP_RESET%>">
					</td>
					<%
						}
					%>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>