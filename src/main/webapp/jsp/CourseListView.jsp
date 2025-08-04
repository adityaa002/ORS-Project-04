<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.controller.CourseListCtl"%>
<%@page import="in.co.rays.bean.CourseBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Course List</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<%@include file="Header.jsp"%>


	<jsp:useBean id="bean" class="in.co.rays.bean.CourseBean"
		scope="request"></jsp:useBean>
	<div align="center">

		<h1 style="color: navy;">Course List</h1>
		<div>

			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>

		<form action="<%=ORSView.COURSE_LIST_CTL%>" method="post">

			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextListSize = (Integer) request.getAttribute("nextListSize");
				List<CourseBean> cList = (List<CourseBean>) request.getAttribute("cList");

				List list = ServletUtility.getList(request);
				Iterator<CourseBean> it = list.iterator();

				if (list.size() > 0) {
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table>
				<tr>
					<td align="center"><label><b>Course Name : </b></label> <%=HTMLUtility.getList("courseId", String.valueOf(bean.getName()), cList)%>&emsp;

						<input type="submit" name="operation"
						value="<%=CourseListCtl.OP_SEARCH%>"> &nbsp; <input
						type="submit" name="operation" value="<%=CourseListCtl.OP_RESET%>">

					</td>
				</tr>
			</table>

			<br>

			<table border="1" style="width: 100%; border: groove;">
				<tr style="background-color: #e1e6f1e3;">
					<th width="2%"><input type="checkbox" id="selectall" /></th>

					<th>S.No</th>
					<th>Name</th>
					<th>Duration</th>
					<th>Description</th>
					<th>Edit</th>
				</tr>

				<%
					while (it.hasNext()) {
							bean = it.next();
				%>

				<tr>
					<td align="center"><input type="checkbox" name="ids" class="case"
						value="<%=bean.getId()%>"></td>
					<td align="center"><%=index++%></td>
					<td align="center"><%=bean.getName()%></td>
					<td align="center"><%=bean.getDuration()%></td>
					<td align="center"><%=bean.getDescription()%></td>
					<td align="center"><a href="CourseCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>

				<%
					}
				%>
			</table>

			<table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						value="<%=CourseListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td align="center"><input type="submit" name="operation"
						value="<%=CourseListCtl.OP_NEW%>"></td>
					<td align="center"><input type="submit" name="operation"
						value="<%=CourseListCtl.OP_DELETE%>"></td>
					<td align="right"><input type="submit" name="operation"
						value="<%=CourseListCtl.OP_NEXT%>"
						<%=nextListSize > 0 ? "" : "disabled"%>></td>
				</tr>
			</table>

			<%
				} else {
			%>
			<p>No records found.</p>
			<table>
				<tr>
					<td><input type="submit" name="operation"
						value="<%=CourseListCtl.OP_BACK%>"></td>
				</tr>
			</table>
			<%
				}
			%>

		</form>
	</div>

</body>
</html>