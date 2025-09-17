package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.UserBean;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.ServletUtility;

/**
 * Abstract base controller providing common constants, utility methods, and
 * request handling logic for all controllers. Ensures validation, preloading,
 * and audit field population.
 * 
 * @author Aditya
 * @version 1.0
 * @since 2025
 */
public abstract class BaseCtl extends HttpServlet {

	private static Logger log = Logger.getLogger(BaseCtl.class);

	public static final String OP_SAVE = "Save";
	public static final String OP_UPDATE = "Update";
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
	public static final String OP_RESET = "Reset";
	public static final String OP_LOG_OUT = "Logout";

	public static final String MSG_SUCCESS = "success";
	public static final String MSG_ERROR = "error";

	/** Validates input data. Default returns true. */
	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	/** Preloads data before processing. */
	protected void preload(HttpServletRequest request) {
		log.debug("BaseCtl preload called");
	}

	/** Populates a bean with request parameters. */
	protected BaseBean populateBean(HttpServletRequest request) {
		return null;
	}

	/** Sets audit fields (createdBy, modifiedBy, timestamps). */
	protected BaseBean populateDto(BaseBean dto, HttpServletRequest request) {

		String createdBy = request.getParameter("createdBy");
		String modifiedBy = null;

		UserBean userbean = (UserBean) request.getSession().getAttribute("user");

		if (userbean == null) {
			createdBy = "root";
			modifiedBy = "root";
		} else {
			modifiedBy = userbean.getLogin();
			if ("null".equalsIgnoreCase(createdBy) || DataValidator.isNull(createdBy)) {
				createdBy = modifiedBy;
			}
		}
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);

		long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));

		if (cdt > 0) {
			dto.setCreatedDatetime(DataUtility.getTimestamp(cdt));
		} else {
			dto.setCreatedDatetime(DataUtility.getCurrentTimestamp());
		}

		dto.setModifiedDatetime(DataUtility.getCurrentTimestamp());
		return dto;

	}

	/** Service method handling preload, validation and request dispatch. */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("BaseCtl service method called");

		preload(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		log.debug("Operation get in BaseCtl is : " + op);

		if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op) && !OP_VIEW.equalsIgnoreCase(op)
				&& !OP_DELETE.equalsIgnoreCase(op) && !OP_RESET.equalsIgnoreCase(op)) {
			
			log.debug("those 5 conditions are getting true");

			if (!validate(request)) {
				BaseBean bean = populateBean(request);
				ServletUtility.setBean(bean, request);
				ServletUtility.forward(getView(), request, response);

				log.debug("view: " + getView());
				return;
			}
		}
		log.debug("super.service() in BaseCtl ---> called");

		super.service(request, response);
	}

	/** Returns the view path for this controller. */
	protected abstract String getView();
}
