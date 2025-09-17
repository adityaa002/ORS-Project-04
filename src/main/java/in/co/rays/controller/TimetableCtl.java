package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.TimetableBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModel;
import in.co.rays.model.SubjectModel;
import in.co.rays.model.TimetableModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * TimetableCtl handles creation, update, validation, and preload of timetable data.
 * It ensures unique exam schedules and prevents conflicts by course, subject, and semester.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
@WebServlet(name = "TimetableCtl", urlPatterns = { "/ctl/TimetableCtl" })
public class TimetableCtl extends BaseCtl {
	
	private static Logger log  = Logger.getLogger(TimetableCtl.class);

	/**
	 * Preloads subjects and courses for selection in timetable form.
	 *
	 * @param request HttpServletRequest
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		SubjectModel subjectModel = new SubjectModel();
		CourseModel courseModel = new CourseModel();
		try {
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
	 * Validates timetable input fields including semester, exam date/time, description, course, and subject.
	 *
	 * @param request HttpServletRequest
	 * @return true if all validations pass; false otherwise
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("TimetableCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("semester"))) {
			request.setAttribute("semester", PropertyReader.getValue("error.require", "Semester"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("examDate"))) {
			request.setAttribute("examDate", PropertyReader.getValue("error.require", "Date of Exam"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("examDate"))) {
			request.setAttribute("examDate", PropertyReader.getValue("error.date", "Date of Exam"));
			pass = false;
		} else if (DataValidator.isSunday(request.getParameter("examDate"))) {
			request.setAttribute("examDate", "Exam should not be on Sunday");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("examTime"))) {
			request.setAttribute("examTime", PropertyReader.getValue("error.require", "Exam Time"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
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

		log.debug("TimetableCtl validate method ended with status : "+ pass);
		return pass;
	}

	/**
	 * Populates TimetableBean from request parameters.
	 *
	 * @param request HttpServletRequest
	 * @return populated TimetableBean
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("TimetableCtl populateBean method started");

		TimetableBean bean = new TimetableBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setSemester(DataUtility.getString(request.getParameter("semester")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setExamTime(DataUtility.getString(request.getParameter("examTime")));
		bean.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));

		populateDto(bean, request);
		log.debug("TimetableCtl populateBean method ended");

		return bean;
	}

	/**
	 * Handles GET request for loading timetable form or editing existing timetable.
	 *
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("TimetableCtl doget method started");

		String op = DataUtility.getString(request.getParameter("operation"));
		TimetableModel model = new TimetableModel();
		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			TimetableBean bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("TimetableCtl doget method ended");
	}

	/**
	 * Handles POST request for adding, updating, resetting, or canceling timetable operations.
	 *
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("TimetableCtl dopost method started");

		String op = DataUtility.getString(request.getParameter("operation"));
		TimetableModel model = new TimetableModel();
		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {
			TimetableBean bean = (TimetableBean) populateBean(request);
			TimetableBean bean1;
			TimetableBean bean2;
			TimetableBean bean3;

			try {
				bean1 = model.checkByCourseName(bean.getCourseId(), bean.getExamDate());
				bean2 = model.checkBySubjectName(bean.getCourseId(), bean.getSubjectId(), bean.getExamDate());
				bean3 = model.checkBySemester(bean.getCourseId(), bean.getSubjectId(), bean.getSemester(), bean.getExamDate());

				if (bean1 == null && bean2 == null && bean3 == null) {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Timetable added successfully", request);
				} else {
					bean = (TimetableBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Timetable already exist!", request);
				}
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Timetable already exist!", request);
			}

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			TimetableBean bean = (TimetableBean) populateBean(request);
			TimetableBean bean4;

			try {
				bean4 = model.checkByExamTime(bean.getCourseId(), bean.getSubjectId(), bean.getSemester(),
						bean.getExamDate(), bean.getExamTime(), bean.getDescription());

				if (id > 0 && bean4 == null) {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Timetable updated successfully", request);
				} else {
					bean = (TimetableBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Timetable already exist!", request);
				}

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Timetable already exist!", request);
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("TimetableCtl dopost method ended");
	}

	/**
	 * Returns the view page for timetable form.
	 *
	 * @return String representing the view path
	 */
	@Override
	protected String getView() {
		return ORSView.TIMETABLE_VIEW;
	}
}
