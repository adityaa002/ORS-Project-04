package in.co.rays.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.PatientBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.PatientModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "PatientCtl", urlPatterns = { "/PatientCtl" })
public class PatientCtl extends BaseCtl {
	/*
	 * @Override protected void preload(HttpServletRequest request) {
	 * 
	 * HashMap<String, String> dMap = new HashMap<String, String>();
	 * dMap.put("Fever", "Fever"); dMap.put("Cold", "Cold"); dMap.put("Cough",
	 * "Cough"); dMap.put("Malaria", "Malaria"); dMap.put("Dengue", "Dengue");
	 * dMap.put("Typhoid", "Typhoid"); dMap.put("Diabetes", "Diabetes");
	 * dMap.put("Hypertension", "Hypertension"); dMap.put("Asthma", "Asthma");
	 * dMap.put("Allergy", "Allergy"); dMap.put("Headache", "Headache");
	 * dMap.put("Migraine", "Migraine");
	 * 
	 * request.setAttribute("dMap", dMap);
	 * 
	 * }
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(DataUtility.getString(request.getParameter("name")))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;

		} else if (!DataValidator.isName(DataUtility.getString(request.getParameter("name")))) {
			request.setAttribute("name", "Enter valid Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "mobile"));
			pass = false;
		} else if (!DataValidator.isPhoneLength(request.getParameter("mobile"))) {
			request.setAttribute("mobile", "Mobile No must have 10 digits");
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobile"))) {
			request.setAttribute("mobile", "Invalid Mobile No");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("disease"))) {
			request.setAttribute("disease", PropertyReader.getValue("error.require", "Disease"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date of Birth"));
			pass = false;
		}

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		PatientBean bean = new PatientBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setMobile(DataUtility.getString(request.getParameter("mobile")));
		bean.setDisease(DataUtility.getString(request.getParameter("disease")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		populateDto(bean, request);
		return bean;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		long id = DataUtility.getLong(request.getParameter("id"));

		PatientModel model = new PatientModel();

		if (id > 0 || op != null) {
			try {
				PatientBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		PatientModel model = new PatientModel();

		System.out.println("patienntCtl dopost");
		if (OP_SAVE.equalsIgnoreCase(op)) {
			System.out.println("patienntCtl svae");
			PatientBean bean = (PatientBean) populateBean(request);
			try {
				model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Patient added successfully..!", request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Patient already exist..!", request);
				e.printStackTrace();
				return;

			}

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			PatientBean bean = (PatientBean) populateBean(request);

			try {
				if (bean.getId() > 0) {

					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Patient updated successfully..!", request);
				}
			} catch (ApplicationException e) {

				ServletUtility.handleException(e, request, response);
				e.printStackTrace();

			} catch (DuplicateRecordException e) {

				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Patient already exist..!", request);
				e.printStackTrace();
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PATIENT_LIST_CTL, request, response);

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PATIENT_CTL, request, response);

		}

	}

	@Override
	protected String getView() {
		return ORSView.PATIENT_VIEW;
	}

}
