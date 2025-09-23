package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.DoctorBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.DoctorModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "DoctorCtl", urlPatterns = { "/ctl/DoctorCtl" })
public class DoctorCtl extends BaseCtl {
	@Override
	protected void preload(HttpServletRequest request) {
		DoctorModel model = new DoctorModel();

		try {
			List<DoctorBean> expertiseList = model.list();
			request.setAttribute("expertiseList", expertiseList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		String dob = request.getParameter("dob");

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "Invalid Name");
			pass = false;
		}

		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date of Birth"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("expertise"))) {
			request.setAttribute("expertise", PropertyReader.getValue("error.require", "Expertise"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "MobileNo"));
			pass = false;
		} else if (!DataValidator.isPhoneLength(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Mobile No must have 10 digits");
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Invalid Mobile No");
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		DoctorBean bean = new DoctorBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setMobile(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setExpertise(DataUtility.getString(request.getParameter("expertise")));

		populateDto(bean, request);

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DoctorModel model = new DoctorModel();
		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			try {
				DoctorBean bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		DoctorModel model = new DoctorModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			DoctorBean bean = (DoctorBean) populateBean(request);
			try {
				model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Doctor added successfully", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				return;
			}
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			DoctorBean bean = (DoctorBean) populateBean(request);
			try {
				if (bean.getId() > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully updated", request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.DOCTOR_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.DOCTOR_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.DOCTOR_VIEW;
	}

}
