package in.co.rays.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.controller.BaseCtl;
import in.co.rays.controller.ORSView;

/**
 * ServletUtility provides helper methods to manage request, response,
 * and servlet navigation tasks such as forwarding, redirecting, and
 * setting or retrieving request attributes.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
public class ServletUtility {

	/**
	 * Forwards the request to a specified page.
	 * 
	 * @param page     Target page
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws IOException
	 * @throws ServletException
	 */
	public static void forward(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	/**
	 * Redirects the response to a specified page.
	 * 
	 * @param page     Target page
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws IOException
	 * @throws ServletException
	 */
	public static void redirect(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.sendRedirect(page);
	}

	/**
	 * Retrieves an error message attribute from the request.
	 * 
	 * @param property Attribute name
	 * @param request  HttpServletRequest object
	 * @return Value of the attribute or empty string if null
	 */
	public static String getErrorMessage(String property, HttpServletRequest request) {
		String val = (String) request.getAttribute(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Retrieves a generic message attribute from the request.
	 * 
	 * @param property Attribute name
	 * @param request  HttpServletRequest object
	 * @return Value of the attribute or empty string if null
	 */
	public static String getMessage(String property, HttpServletRequest request) {
		String val = (String) request.getAttribute(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Sets an error message in the request attributes.
	 * 
	 * @param msg     Message string
	 * @param request HttpServletRequest object
	 */
	public static void setErrorMessage(String msg, HttpServletRequest request) {
		request.setAttribute(BaseCtl.MSG_ERROR, msg);
	}

	/**
	 * Retrieves the standard error message from the request.
	 * 
	 * @param request HttpServletRequest object
	 * @return Error message or empty string if null
	 */
	public static String getErrorMessage(HttpServletRequest request) {
		String val = (String) request.getAttribute(BaseCtl.MSG_ERROR);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Sets a success message in the request attributes.
	 * 
	 * @param msg     Message string
	 * @param request HttpServletRequest object
	 */
	public static void setSuccessMessage(String msg, HttpServletRequest request) {
		request.setAttribute(BaseCtl.MSG_SUCCESS, msg);
	}

	/**
	 * Retrieves the standard success message from the request.
	 * 
	 * @param request HttpServletRequest object
	 * @return Success message or empty string if null
	 */
	public static String getSuccessMessage(HttpServletRequest request) {
		String val = (String) request.getAttribute(BaseCtl.MSG_SUCCESS);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Sets a BaseBean object in the request attributes.
	 * 
	 * @param bean    BaseBean object
	 * @param request HttpServletRequest object
	 */
	public static void setBean(BaseBean bean, HttpServletRequest request) {
		request.setAttribute("bean", bean);
	}

	/**
	 * Retrieves the BaseBean object from the request.
	 * 
	 * @param request HttpServletRequest object
	 * @return BaseBean object or null if not set
	 */
	public static BaseBean getBean(HttpServletRequest request) {
		return (BaseBean) request.getAttribute("bean");
	}

	/**
	 * Retrieves a parameter value from the request.
	 * 
	 * @param property Parameter name
	 * @param request  HttpServletRequest object
	 * @return Parameter value or empty string if null
	 */
	public static String getParameter(String property, HttpServletRequest request) {
		String val = (String) request.getParameter(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Sets a list attribute in the request.
	 * 
	 * @param list    List object
	 * @param request HttpServletRequest object
	 */
	public static void setList(List list, HttpServletRequest request) {
		request.setAttribute("list", list);
	}

	/**
	 * Retrieves the list attribute from the request.
	 * 
	 * @param request HttpServletRequest object
	 * @return List object or null if not set
	 */
	public static List getList(HttpServletRequest request) {
		return (List) request.getAttribute("list");
	}

	/**
	 * Sets the page number in the request attributes.
	 * 
	 * @param pageNo  Page number
	 * @param request HttpServletRequest object
	 */
	public static void setPageNo(int pageNo, HttpServletRequest request) {
		request.setAttribute("pageNo", pageNo);
	}

	/**
	 * Retrieves the page number from the request attributes.
	 * 
	 * @param request HttpServletRequest object
	 * @return Page number
	 */
	public static int getPageNo(HttpServletRequest request) {
		return (Integer) request.getAttribute("pageNo");
	}

	/**
	 * Sets the page size in the request attributes.
	 * 
	 * @param pageSize Page size
	 * @param request  HttpServletRequest object
	 */
	public static void setPageSize(int pageSize, HttpServletRequest request) {
		request.setAttribute("pageSize", pageSize);
	}

	/**
	 * Retrieves the page size from the request attributes.
	 * 
	 * @param request HttpServletRequest object
	 * @return Page size
	 */
	public static int getPageSize(HttpServletRequest request) {
		return (Integer) request.getAttribute("pageSize");
	}

	/**
	 * Handles exceptions by setting them in request attributes and
	 * redirecting to the error controller.
	 * 
	 * @param e       Exception object
	 * @param request HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws IOException
	 * @throws ServletException
	 */
	public static void handleException(Exception e, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setAttribute("exception", e);
		response.sendRedirect(ORSView.ERROR_CTL);
	}
}
