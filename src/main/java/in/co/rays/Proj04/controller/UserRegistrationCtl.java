package in.co.rays.Proj04.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.Proj04.bean.BaseBean;
import in.co.rays.Proj04.bean.RoleBean;
import in.co.rays.Proj04.bean.UserBean;
import in.co.rays.Proj04.exception.DuplicateRecordException;
import in.co.rays.Proj04.model.UserModel;
import in.co.rays.Proj04.util.DataUtility;
import in.co.rays.Proj04.util.DataValidator;
import in.co.rays.Proj04.util.ServletUtility;

@WebServlet(name = "UserRegistrationCtl", urlPatterns = { "/UserRegistrationCtl" })
public class UserRegistrationCtl extends BaseCtl {

	public static final String OP_SIGN_UP = "SignUp";

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("in validate method");

		boolean isValid = true;
		if (DataValidator.isNull(request.getParameter("firstName"))) {
			isValid = false;
			request.setAttribute("firstName", "first name is required");
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			isValid = false;
			request.setAttribute("lastName", "last name is required");
		}

		if (DataValidator.isNull(request.getParameter("login"))) {
			isValid = false;
			request.setAttribute("login", "login is required");
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			isValid = false;
			request.setAttribute("password", "password is required");
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			isValid = false;
			request.setAttribute("gender", "gender is required");
		}

		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			isValid = false;
			request.setAttribute("confirmPassword", "confirmPassword is required");
		} else if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))) {
			isValid = false;
			request.setAttribute("confirmPassword", "confirmPassword and password must be same");
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			isValid = false;
			request.setAttribute("mobileNo", "mobileNo is required");
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			isValid = false;
			request.setAttribute("dob", "dob is required");
		}

		return isValid;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		UserBean bean = new UserBean();
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setRoleId(RoleBean.STUDENT);

		System.out.println(request.getParameter("firstName"));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do get method");
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do post method");

		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("operation: " + op);
		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		bean = (UserBean) populateBean(request);

		if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			try {
				model.add(bean);
				System.out.println(" executed add method ");
				ServletUtility.setSuccessMessage("User registered successfully", request);
			} catch (Exception e) {
				ServletUtility.setErrorMessage(e.getMessage(), request);
				e.printStackTrace(System.out);
			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.USER_REGISTRATION_VIEW;
	}

}
