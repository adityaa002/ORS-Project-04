<%@page import="in.co.rays.controller.ForgetPasswordCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forget Password</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
    <form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">

        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.rays.bean.UserBean" scope="request"></jsp:useBean>

        <div align="center">
            <h1 align="center" style="margin-bottom: -15; color: navy">Forgot your password?</h1>

            <div style="height: 15px; margin-bottom: 12px">
                <h3 align="center">
                    <font color="green">
                        <%=ServletUtility.getSuccessMessage(request)%>
                    </font>
                </h3>
                <h3 align="center">
                    <font color="red">
                        <%=ServletUtility.getErrorMessage(request)%>
                    </font>
                </h3>
            </div>

            <input type="hidden" name="id" value="<%=bean.getId()%>">

            <h3 style="margin-bottom: -10;">
                <label>Submit your email address and we'll send you password.</label>
            </h3>

            <table>
                <tr>
                    <th align="left">
                        Email Id<span style="color: red">*</span>&nbsp;
                    </th>&emsp;
                    <td align="center">
                        <input type="text" name="login" placeholder="Enter Email ID Here"
                            value="<%=ServletUtility.getParameter("login", request)%>">&nbsp;
                    </td>
                    <td align="center">
                        <input type="submit" name="operation" value="<%=ForgetPasswordCtl.OP_GO%>">
                    </td>
                    <td style="position: fixed;">
                        <font color="red">
                            <%=ServletUtility.getErrorMessage("login", request)%>
                        </font>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</body>
</html>