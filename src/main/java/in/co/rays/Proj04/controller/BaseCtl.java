package in.co.rays.Proj04.controller;

import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.Proj04.bean.BaseBean;
import in.co.rays.Proj04.bean.UserBean;

public abstract class BaseCtl extends HttpServlet {
	public static final String OP_SAVE = "Save";
	public static final String OP_CANCEL = "Cancel";
	public static final String OP_DELETE = "Delete";
	public static final String OP_LIST = "List";
	public static final String OP_SEARCH = "Search";
	public static final String OP_VIEW = "View";
	public static final String OP_NEXT = "Next";
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_GO = "Go";
	public static final String OP_BACK = "Back";
	public static final String OP_LOG_OUT = "Logout";
	public static final String OP_RESET = "Reset";
	public static final String OP_UPDATE = "update";

	/**
	 * Success message key constant
	 */
	public static final String MSG_SUCCESS = "success";

	/**
	 * Error message key constant
	 */

	public static final String MSG_ERROR = "error";

	/**
	 * Validates input data entered by User
	 *
	 * @param request
	 * @return
	 */
	protected boolean validate(HttpServletRequest request) {
		return true;

	}

	/**
	 * Loads list and other data required to display at HTML form
	 *
	 * @param request
	 */
	public void preload(HttpServletRequest request) {

	}

	/**
	 * Populates bean object from request parameters
	 *
	 * @param request
	 * @return
	 */
	public BaseBean populates(HttpServletRequest request) {
		return null;

	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("mai sbse pehle chalunga");

		super.service(request, response);
	}

	protected abstract String getView();

}
