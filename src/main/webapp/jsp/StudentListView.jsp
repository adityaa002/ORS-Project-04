<%@page import="java.text.SimpleDateFormat"%>
<%@page import="in.co.rays.bean.CollegeBean"%>
<%@page import="in.co.rays.model.CollegeModel"%>
<%@page import="in.co.rays.controller.StudentListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.bean.StudentBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student List</title>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
    <%@include file="Header.jsp"%>
    <div align="center">
        <h1 align="center" style="margin-bottom: -15; color: navy;">Student List</h1>

        <div style="height: 15px; margin-bottom: 12px">
            <h3>
                <font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
            </h3>
            <h3>
                <font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
            </h3>
        </div>

        <form action="<%=ORSView.STUDENT_LIST_CTL%>" method="post">
            <%
                int pageNo = ServletUtility.getPageNo(request);
                int pageSize = ServletUtility.getPageSize(request);
                int index = ((pageNo - 1) * pageSize) + 1;
                int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

                 List<StudentBean> list = (List<StudentBean>) ServletUtility.getList(request);
                Iterator<StudentBean> it = list.iterator();

                if (list.size() != 0) {
            %>

            <input type="hidden" name="pageNo" value="<%=pageNo%>">
            <input type="hidden" name="pageSize" value="<%=pageSize%>">

            <table style="width: 100%">
                <tr>
                    <td align="center">
                        <label><b>First Name :</b></label>
                        <input type="text" name="firstName" placeholder="Enter First Name"
                               value="<%=ServletUtility.getParameter("firstName", request)%>">&emsp;
                        <label><b>Last Name :</b></label>
                        <input type="text" name="lastName" placeholder="Enter Last Name"
                               value="<%=ServletUtility.getParameter("lastName", request)%>">&emsp;
                        <label><b>Email Id :</b></label>
                        <input type="text" name="email" placeholder="Enter Email Id"
                               value="<%=ServletUtility.getParameter("email", request)%>">&emsp;
                        <input type="submit" name="operation" value="<%=StudentListCtl.OP_SEARCH%>">&nbsp;
                        <input type="submit" name="operation" value="<%=StudentListCtl.OP_RESET%>">
                    </td>
                </tr>
            </table>
            <br>

            <table border="1" style="width: 100%; border: groove;">
                <tr style="background-color: #e1e6f1e3;">
                    <th width="3%"><input type="checkbox" id="selectall" /></th>
                    <th width="3%">S.No</th>
                    <th width="7%">First Name</th>
                    <th width="7%">Last Name</th>
                    <th width="12%">Email Id</th>
                    <th width="19%">College Name</th>
                    <th width="5%">Gender</th>
                    <th width="7%">Mobile No</th>
                    <th width="7%">Date of Birth</th>
                    <th width="3%">Edit</th>
                </tr>

                <%
                    while (it.hasNext()) {
                        StudentBean bean = it.next();
                %>
                <tr>
                    <td style="text-align: center;"><input type="checkbox" class="case" name="ids" value="<%=bean.getId()%>"></td>
                    <td style="text-align: center;"><%=index++%></td>
                    <td style="text-align: center; text-transform: capitalize;"><%=bean.getFirstName()%></td>
                    <td style="text-align: center; text-transform: capitalize;"><%=bean.getLastName()%></td>
                    <td style="text-align: center; text-transform: lowercase;"><%=bean.getEmail()%></td>
                    <td style="text-align: center; text-transform: capitalize;"><%=bean.getCollegeName()%></td>
                    <td style="text-align: center; text-transform: capitalize;"><%=bean.getGender()%></td>
                    <td style="text-align: center;"><%=bean.getMobileNo()%></td>
                    <%
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        String date = sdf.format(bean.getDob());
                    %>
                    <td style="text-align: center;"><%=date%></td>
                    <td style="text-align: center;"><a href="StudentCtl?id=<%=bean.getId()%>">Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>

            <table style="width: 100%">
                <tr>
                    <td style="width: 25%">
                        <input type="submit" name="operation" value="<%=StudentListCtl.OP_PREVIOUS%>" <%=pageNo > 1 ? "" : "disabled"%>>
                    </td>
                    <td align="center" style="width: 25%">
                        <input type="submit" name="operation" value="<%=StudentListCtl.OP_NEW%>">
                    </td>
                    <td align="center" style="width: 25%">
                        <input type="submit" name="operation" value="<%=StudentListCtl.OP_DELETE%>">
                    </td>
                    <td style="width: 25%" align="right">
                        <input type="submit" name="operation" value="<%=StudentListCtl.OP_NEXT%>" <%= (nextPageSize != 0) ? "" : "disabled"%>>
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
                        <input type="submit" name="operation" value="<%=StudentListCtl.OP_BACK%>">
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