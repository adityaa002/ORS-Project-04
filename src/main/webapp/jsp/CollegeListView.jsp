<%@page import="in.co.rays.controller.CollegeListCtl"%>
<%@page import="in.co.rays.controller.CollegeCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.bean.CollegeBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>College List</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<%@include file="Header.jsp"%>
	<jsp:useBean id="bean" class="in.co.rays.bean.CollegeBean"
		scope="request"></jsp:useBean>
	<div align="center">
		<h1 align="center" style="margin-bottom: -15; color: navy;">College
			List</h1>

		<div>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
		</div>


		<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="post">

			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
                int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

				List<CollegeBean> collegeList = (List<CollegeBean>) request.getAttribute("collegeList");
				List<CollegeBean> list = (List<CollegeBean>) ServletUtility.getList(request);
				Iterator<CollegeBean> it = list.iterator();

				if (list.size() != 0) {
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">


			<table>

				<tr>
					<td align="center"><label>College Name:</label> <%=HTMLUtility.getList("collegeId", String.valueOf(bean.getId()), collegeList)%>&emsp;
					</td>
					<td align="center"><label>City:</label> <input type="text"
						name="city"
						value="<%=ServletUtility.getParameter("city", request)%>"
						placeholder="Enter City">&nbsp;</td>
					<td><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_SEARCH%>"></td>
				</tr>
			</table>
			<br>


			<table border="1" style="width: 100%; border: groove;">
				<tr style="background-color: #e1e6f1e3;">
					<th width="2%"><input type="checkbox" id="selectall" /></th>
					<th width="2%">S.No</th>
					<th width="13%">College Name</th>
					<th width="13%">Address</th>
					<th width="8%">State</th>
					<th width="7%">City</th>
					<th width="7%">Phone No</th>
					<th width="3%">Edit</th>
				</tr>

				<%
					while (it.hasNext()) {
							bean = (CollegeBean) it.next();
				%>



				<tr>
					<td style="text-align: center;"><input type="checkbox"
						name="ids" value="<%=bean.getId()%>"></td>
					<td style="text-align: center;"><%=index++%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getName()%></td>

					<td style="text-align: center; text-transform: capitalize;"><%=bean.getAddress()%></td>
					<td style="text-align: center; text-transform: lowercase;"><%=bean.getState()%></td>

					<td style="text-align: center; text-transform: capitalize;"><%=bean.getCity()%></td>

					<td style="text-align: center; text-transform: capitalize;"><%=bean.getPhoneNo()%></td>
					<td style="text-align: center;"><a
						href="CollegeCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>

				<%
					}
				%>

			</table>
			  <table style="width: 100%">
                <tr>
                    <td style="width: 25%">
                        <input type="submit" name="operation" value="<%=CollegeListCtl.OP_PREVIOUS%>" <%=pageNo > 1 ? "" : "disabled"%>>
                    </td>
                    <td align="center" style="width: 25%">
                        <input type="submit" name="operation" value="<%=CollegeListCtl.OP_NEW%>">
                    </td>
                    <td align="center" style="width: 25%">
                        <input type="submit" name="operation" value="<%=CollegeListCtl.OP_DELETE%>">
                    </td>
                    <td style="width: 25%" align="right">
                        <input type="submit" name="operation" value="<%=CollegeListCtl.OP_NEXT%>" <%=(nextPageSize != 0) ? "" : "disabled" %>>
                    </td>
                </tr>
            </table>

            <%
                }
                if (list.size() == 0) {
            %>
            <table>
                <tr>
                    <td align="right">
                        <input type="submit" name="operation" value="<%=CollegeListCtl.OP_BACK%>">
                    </td>
                </tr>
            </table>
            <%
                }
            %>
        </form>
	</div>
</body>
</html>