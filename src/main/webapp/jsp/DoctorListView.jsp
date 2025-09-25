<%@page import="java.util.HashMap"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="in.co.rays.controller.DoctorListCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.bean.DoctorBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor List</title>
</head>
<body>

	<%@include file="Header.jsp"%>

	<jsp:useBean id="bean" class="in.co.rays.bean.DoctorBean"
		scope="request"></jsp:useBean>

	<div align="center">
		<h1 align="center" style="margin-bottom: -15; color: navy;">Doctor
			List ⚕️ </h1>

		<div style="height: 15px; margin-bottom: 12px">
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>

		<form action="<%=ORSView.DOCTOR_LIST_CTL%>" method="post">
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextListSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

				List<DoctorBean> list = (List<DoctorBean>) ServletUtility.getList(request);
				HashMap<String, String> genderMap = (HashMap<String, String>) request.getAttribute("genderMap");

				Iterator<DoctorBean> it = list.iterator();

				if (list.size() != 0) {
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b> Name :</b></label> <input
						type="text" name="name" placeholder="Enter Name"
						value="<%=ServletUtility.getParameter("name", request)%>">&emsp;

						<label><b>Expertise:</b></label> <%=HTMLUtility.getList("expertise", ServletUtility.getParameter("expertise", request), list)%>&emsp;

						<label><b>Date of birth :</b></label> <input type="text"
						name="dob" placeholder="Enter Date of birth" id="udate"
						value="<%=ServletUtility.getParameter("dob", request)%>">&emsp;

						<input type="submit" name="operation"
						value="<%=DoctorListCtl.OP_SEARCH%>"> &nbsp; <input
						type="submit" name="operation" value="<%=DoctorListCtl.OP_RESET%>">
					</td>
				</tr>
			</table>
			<br>

			<table border="1" style="width: 100%; border: groove;">
				<tr style="background-color: #e1e6f1e3;">

					<th width="2%"><input type="checkbox" id="selectall" /></th>
					<th width="5%">S.No</th>
					<th width="13%">Name</th>
					<th width="10%">Expertise</th>
					<th width="9%">Mobile No.</th>
					<th width="9%">Gender</th>
					<th width="9%">Date of Birth</th>
					<th width="5%">Edit</th>
				</tr>

				<%
					while (it.hasNext()) {
							bean = (DoctorBean) it.next();

							SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/DD");
							String date = sdf.format(bean.getDob());
				%>

				<tr>
					<td style="text-align: center;"><input type="checkbox"
						name="ids" class="case" value="<%=bean.getId()%>"></td>
					<td style="text-align: center;"><%=index++%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getName()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getExpertise()%></td>
					<td style="text-align: center;"><%=bean.getMobile()%></td>
					<td style="text-align: center;"><%=bean.getGender()%></td>
					<td style="text-align: center;"><%=date%></td>

					<td style="text-align: center;"><a
						href="DoctorCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>

				<%
					}
				%>
			</table>

			<table style="width: 100%">
				<tr>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=DoctorListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=DoctorListCtl.OP_NEW%>"></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=DoctorListCtl.OP_DELETE%>"></td>
					<td style="width: 25%" align="right"><input type="submit"
						name="operation" value="<%=DoctorListCtl.OP_NEXT%>"
						<%=nextListSize != 0 ? "" : "disabled"%>></td>
				</tr>
			</table>

			<%
				} else {
			%>

			<table>
				<tr>
					<td align="right"><input type="submit" name="operation"
						value="<%=DoctorListCtl.OP_BACK%>"></td>
				</tr>
			</table>

			<%
				}
			%>
		</form>
	</div>
	<%@include file="Footer.jsp"%>

</body>
</html>
