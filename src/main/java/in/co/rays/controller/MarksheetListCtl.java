package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.MarksheetBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.MarksheetModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * MarksheetListCtl servlet class to handle listing, searching, and deleting
 * marksheet records.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 * 
 */
@WebServlet(name = "MarksheetListCtl", urlPatterns = { "/ctl/MarksheetListCtl" })
public class MarksheetListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(MarksheetListCtl.class);

	/**
	 * Populates MarksheetBean from request parameters.
	 *
	 * @param request the HttpServletRequest object
	 * @return populated MarksheetBean
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("MarksheetListCtl populateBean method started");

		MarksheetBean bean = new MarksheetBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

		populateDto(bean, request);

		log.debug("MarksheetListCtl populateBean method ended");
		return bean;
	}

	/**
	 * Handles HTTP GET requests.
	 * 
	 * Loads the first page of marksheet records based on search criteria.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("MarksheetListCtl doget method started");

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		MarksheetModel model = new MarksheetModel();

		try {
			MarksheetBean bean = (MarksheetBean) populateBean(request);

			List<MarksheetBean> list = model.search(bean, pageNo, pageSize);
			List<MarksheetBean> next = model.search(bean, pageNo + 1, pageSize);

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			ServletUtility.setList(list, request);

			request.setAttribute("nextListSize", next.size());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("MarksheetListCtl doget method ended");

	}

	/**
	 * Handles HTTP POST requests.
	 * 
	 * Performs operations like search, pagination, delete, reset, etc.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("MarksheetListCtl dopost method started");

		List list = null;
		List next = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		MarksheetBean bean = (MarksheetBean) populateBean(request);
		MarksheetModel model = new MarksheetModel();

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
				ServletUtility.redirect(ORSView.MARKSHEET_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					MarksheetBean deletebean = new MarksheetBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
						ServletUtility.setSuccessMessage("Marksheet is deleted successfully", request);
					}
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
				return;
			} else if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
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

		log.debug("MarksheetListCtl dopost method ended");

	}

	/**
	 * Returns the view path for the Marksheet list JSP.
	 *
	 * @return view path as a String
	 */
	@Override
	protected String getView() {
		return ORSView.MARKSHEET_LIST_VIEW;
	}

}
