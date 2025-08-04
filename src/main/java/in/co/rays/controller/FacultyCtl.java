package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.FacultyBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CollegeModel;
import in.co.rays.model.CourseModel;
import in.co.rays.model.FacultyModel;
import in.co.rays.model.SubjectModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * FacultyCtl is a controller to handle add/update operations on Faculty data.
 * It validates input, populates beans, and calls the model layer for business
 * logic.
 * 
 * @author Aditya
 * @version 1.0
 * @since 2025-08-01
 */
@WebServlet(name = "FacultyCtl", urlPatterns = { "/FacultyCtl" })
public class FacultyCtl extends BaseCtl {

	/**
	 * Preloads college, course, and subject data for dropdowns on Faculty form.
	 *
	 * @param request the HTTP request
	 */
	@Override
	protected void preload(HttpServletRequest request) {

		CollegeModel collegeModel = new CollegeModel();
		SubjectModel subjectModel = new SubjectModel();
		CourseModel courseModel = new CourseModel();

		try {
			List<CollegeBean> collegeList = collegeModel.list();
			request.setAttribute("collegeList", collegeList);

			List subjectList = subjectModel.list();
			request.setAttribute("subjectList", subjectList);

			List courseList = courseModel.list();
			request.setAttribute("courseList", courseList);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Validates input data before processing Faculty form submission.
	 *
	 * @param request the HTTP request
	 * @return true if validation passes; false otherwise
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "Invalid First Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "Invalid Last Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date of Birth"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getValue("error.require", "Email "));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getValue("error.email", "Email "));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		} else if (!DataValidator.isPhoneLength(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Mobile No must have 10 digits");
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Invalid Mobile No");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId", PropertyReader.getValue("error.require", "College Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("courseId"))) {
			request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subjectId"))) {
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates FacultyBean with request data.
	 *
	 * @param request the HTTP request
	 * @return the populated FacultyBean
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		FacultyBean bean = new FacultyBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));

		populateDto(bean, request);

		return bean;
	}

	/**
	 * Handles HTTP GET request. Loads existing Faculty data if ID is provided.
	 *
	 * @param request  the HTTP request
	 * @param response the HTTP response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		FacultyModel model = new FacultyModel();

		if (id > 0 || op != null) {
			FacultyBean bean;
			try {
				bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Handles HTTP POST request. Processes save, update, reset, and cancel
	 * operations.
	 *
	 * @param request  the HTTP request
	 * @param response the HTTP response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		FacultyModel model = new FacultyModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			FacultyBean bean = (FacultyBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Faculty added successfully", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Email already exists", request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			FacultyBean bean = (FacultyBean) populateBean(request);
			try {
				if (bean.getId() > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Faculty updated successfully", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Email already exists", request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Returns the view path for the Faculty form.
	 *
	 * @return the view page path
	 */
	@Override
	protected String getView() {
		return ORSView.FACULTY_VIEW;
	}
}
