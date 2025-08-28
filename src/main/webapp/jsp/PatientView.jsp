<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.controller.PatientCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.bean.PatientBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add User</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<%@include file="Header.jsp"%>
	<jsp:useBean id="bean" class="in.co.rays.bean.PatientBean"
		scope="request"></jsp:useBean>



	<form action="<%=ORSView.PATIENT_CTL%>" method="post">
		<div align="center">
			<h1 align="center" style="margin-bottom: -15; color: navy">
				<%
					if (bean != null && bean.getId() > 0) {
				%>Update<%
					} else {
				%>Add<%
					}
				%>
				Patient
			</h1>
			<div style="height: 15px; margin-bottom: 12px">
				<H3 align="center">
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
					</font>
				</H3>

				<H3 align="center">
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font>
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
					<th align="left">Name<span style="color: red">*</span></th>
					<td><input type="text" name="name" placeholder="Enter Name"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Mobile No:<span style="color: red">*</span></th>
					<td><input type="text" name="mobile" maxlength="10"
						placeholder="Enter Mobile No."
						value="<%=DataUtility.getStringData(bean.getMobile())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("mobile", request)%></font></td>
				</tr>


				<tr>
					<th align="left">Disease:<span style="color: red">*</span></th>
					<td>
						<%
							HashMap<String, String> dMap = new HashMap<String, String>();
							dMap.put("Fever", "Fever");
							dMap.put("Cold", "Cold");
							dMap.put("Cough", "Cough");
							dMap.put("Malaria", "Malaria");
							dMap.put("Dengue", "Dengue");
							dMap.put("Typhoid", "Typhoid");
							dMap.put("Diabetes", "Diabetes");
							dMap.put("Hypertension", "Hypertension");
							dMap.put("Asthma", "Asthma");
							dMap.put("Allergy", "Allergy");
							dMap.put("Headache", "Headache");
							dMap.put("Migraine", "Migraine");
							String htmlList = HTMLUtility.getList("disease", bean.getDisease(), dMap);
						%> <%=htmlList%>
					</td>
				</tr>
				<tr>
					<th align="left">Date of Birth<span style="color: red">*</span></th>
					<td><input type="text" id="udate" name="dob"
						placeholder="Select Date of Birth"
						value="<%=DataUtility.getDateString(bean.getDob())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>




				<th></th>
				<td></td>
				</tr>
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=PatientCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=PatientCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=PatientCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=PatientCtl.OP_RESET%>"></td>
					<%
						}
					%>
				</tr>
			</table>
		</div>
	</form>
	<%@include file="Footer.jsp"%>

</body>
</html>