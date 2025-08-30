package in.co.rays.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.StudentBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CollegeModel;
import in.co.rays.model.StudentModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * StudentCtl servlet handles adding, updating, and preloading student records.
 * It performs validation, populates StudentBean, and forwards requests to the view.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
@WebServlet(name = "StudentCtl", urlPatterns = { "/ctl/StudentCtl" })
public class StudentCtl extends BaseCtl {

	private static Logger log  = Logger.getLogger(StudentCtl.class);

	/**
	 * Preloads college list and gender map for the student form.
	 *
	 * @param request HttpServletRequest
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		CollegeModel model = new CollegeModel();
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("Male", "Male");
			map.put("Female", "Female");
			List<CollegeBean> collegeList = model.list();
			request.setAttribute("genderMap", map);
			request.setAttribute("collegeList", collegeList);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Validates student form input.
	 *
	 * @param request HttpServletRequest
	 * @return boolean true if validation passes, false otherwise
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("StudentCtl validate method started");

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

		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getValue("error.require", "Email Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("email"))) {
			request.setAttribute("email", "Invalid Email Id");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of birth"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("dob"))) {
			request.setAttribute("dob", "Invalid Date of birth");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId", PropertyReader.getValue("error.require", "College Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile No."));
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobileNo"))
				&& !DataValidator.isPhoneLength(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Invalid Mobile no.");
			pass = false;
		}

		log.debug("StudentCtl validate method ended with status : " + pass);
		return pass;
	}

	/**
	 * Populates StudentBean from request parameters.
	 *
	 * @param request HttpServletRequest
	 * @return populated StudentBean
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("StudentCtl populateBean method started");

		StudentBean bean = new StudentBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		populateDto(bean, request);
		log.debug("StudentCtl populateBean method ended");
		return bean;
	}

	/**
	 * Handles GET request to load student form for add/update.
	 *
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("StudentCtl doget method started");

		String op = DataUtility.getString(request.getParameter("operation"));
		StudentModel model = new StudentModel();
		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0 || op != null) {
			try {
				StudentBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("StudentCtl doget method ended");
	}

	/**
	 * Handles POST request to save/update student data.
	 *
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("StudentCtl dopost method started");

		String op = DataUtility.getString(request.getParameter("operation"));
		StudentModel model = new StudentModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			StudentBean bean = (StudentBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Student added successfully", request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Email already exists", request);
			}
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			StudentBean bean = (StudentBean) populateBean(request);
			try {
				if (bean.getId() > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Student updated successfully", request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Email already exists", request);
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.STUDENT_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("StudentCtl dopost method ended");
	}

	/**
	 * Returns the view page for student form.
	 *
	 * @return view path as String
	 */
	@Override
	protected String getView() {
		return ORSView.STUDENT_VIEW;
	}
}
