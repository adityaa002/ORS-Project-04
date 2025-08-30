/**
 * RoleListCtl servlet class handles the role listing, searching, pagination,
 * deletion, and redirection operations in the application.
 * 
 * @author Aditya
 * @version 1.0
 * @since 2025
 */
package in.co.rays.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.RoleModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * Servlet implementation class RoleListCtl
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 *  
 */
@WebServlet(name = "RoleListCtl", urlPatterns = { "/ctl/RoleListCtl" })
public class RoleListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(RoleListCtl.class);

	/**
	 * Preloads the list of roles for the view.
	 *
	 * @param request the HttpServletRequest object
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		RoleModel model = new RoleModel();
		try {
			List<RoleBean> roleList = model.list();
			request.setAttribute("roleList", roleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Populates the RoleBean object from the request parameters.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated RoleBean
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("RoleListCtl populateBean method started");

		RoleBean bean = new RoleBean();
		bean.setId(DataUtility.getLong(request.getParameter("roleId")));
		bean.setName(DataUtility.getString(request.getParameter("name")));

		log.debug("RoleListCtl populateBean method ended");
		return bean;
	}

	/**
	 * Handles the HTTP GET request to list roles with pagination and error
	 * handling.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("RoleListCtl doget method started");

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		RoleModel model = new RoleModel();
		RoleBean bean = new RoleBean();

		try {
			List<RoleBean> list = model.search(bean, pageNo, pageSize);
			List<RoleBean> next = model.search(bean, pageNo + 1, pageSize);

			if (list != null && list.isEmpty()) {
				ServletUtility.setErrorMessage("No record found..!", request);
			}

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			ServletUtility.setList(list, request);

			request.setAttribute("nextListSize", next.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("RoleListCtl doget method ended");

	}

	/**
	 * Handles the HTTP POST request to perform various operations: search,
	 * next/previous pagination, delete, new, reset, and back.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("RoleListCtl dopost method started");

		List list = null;
		List next = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		RoleBean bean = (RoleBean) populateBean(request);
		RoleModel model = new RoleModel();

		String op = DataUtility.getString(request.getParameter("operation"));
		String[] ids = request.getParameterValues("ids");

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.USER_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					RoleBean deletebean = new RoleBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
						ServletUtility.setSuccessMessage("Data is deleted successfully", request);
					}
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
				return;
			} else if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
				return;
			}

			list = model.search(bean, pageNo, pageSize);
			next = model.search(bean, pageNo + 1, pageSize);

			if (!OP_DELETE.equalsIgnoreCase(op)) {
				if (list == null || list.size() == 0) {
					ServletUtility.setErrorMessage("No record found ", request);
				}
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			request.setAttribute("nextListSize", next.size());

			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			return;
		}

		log.debug("RoleListCtl dopost method ended");

	}

	/**
	 * Returns the view for the role list page.
	 *
	 * @return the view path as a String
	 */
	@Override
	protected String getView() {
		return ORSView.ROLE_LIST_VIEW;
	}

}
