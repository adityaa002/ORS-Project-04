package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModel;
import in.co.rays.model.SubjectModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * SubjectCtl servlet handles add, update, cancel, and reset operations for subjects.
 * It also preloads course list for selection.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
@WebServlet(name = "SubjectCtl", urlPatterns = { "/ctl/SubjectCtl" })
public class SubjectCtl extends BaseCtl {

	private static Logger log  = Logger.getLogger(SubjectCtl.class);

	/**
	 * Preloads the course list for subject selection.
	 *
	 * @param request HttpServletRequest
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		CourseModel model = new CourseModel();
		try {
			List courseList = model.list();
			request.setAttribute("courseList", courseList);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Validates the subject form data.
	 *
	 * @param request HttpServletRequest
	 * @return true if valid, false otherwise
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("SubjectCtl validate method started");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("courseId"))) {
			request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}

		log.debug("SubjectCtl validate method ended with status : "+pass);
		return pass;
	}

	/**
	 * Populates SubjectBean from request parameters.
	 *
	 * @param request HttpServletRequest
	 * @return populated SubjectBean
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("SubjectCtl populateBean method started");
		SubjectBean bean = new SubjectBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		populateDto(bean, request);
		log.debug("SubjectCtl populateBean method ended");
		return bean;
	}

	/**
	 * Handles GET request to populate the form for editing a subject.
	 *
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("SubjectCtl doget method started");
		String op = DataUtility.getString(request.getParameter("operation"));
		SubjectModel model = new SubjectModel();
		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			try {
				SubjectBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("SubjectCtl doget method ended");
	}

	/**
	 * Handles POST request for adding or updating a subject.
	 *
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("SubjectCtl dopost method started");
		String op = DataUtility.getString(request.getParameter("operation"));
		SubjectModel model = new SubjectModel();
		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {
			SubjectBean bean = (SubjectBean) populateBean(request);
			try {
				model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Subject added successfully", request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Subject Name already exists", request);
			}
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			SubjectBean bean = (SubjectBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Subject updated successfully", request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Subject Name already exists", request);
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("SubjectCtl dopost method ended");
	}

	/**
	 * Returns the view for the subject form.
	 *
	 * @return String representing the view path
	 */
	@Override
	protected String getView() {
		return ORSView.SUBJECT_VIEW;
	}
}
