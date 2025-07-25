package in.co.rays.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.RoleModel;
import in.co.rays.model.UserModel;
import in.co.rays.testClasses.PropertyReader;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "LoginCtl", urlPatterns = { "/LoginCtl" })
public class LoginCtl extends BaseCtl {

	public static final String OP_SIGN_IN = "Sign In";
	public static final String OP_SIGN_UP = "Sign Up";
	public static final String OP_REGISTER = "Register";
	public static final String OP_LOG_OUT = "Logout";

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}

		if (DataValidator.isNull(request.getParameter("loginId"))) {
			request.setAttribute("loginId", PropertyReader.getValue("error.require", "login Id"));
			pass = false;
			
		} else if (!DataValidator.isEmail(request.getParameter("loginId"))) {
			request.setAttribute("loginId", " Invalid login Id");
			pass = false;

		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "password"));
			pass = false;

		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password","Invalid password");
		}

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setLogin(DataUtility.getString(request.getParameter("loginId")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		return bean;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_LOG_OUT.equals(op)) {
			session.invalidate();
			ServletUtility.setSuccessMessage("Logout Successfully ..!", request);
			ServletUtility.forward(getView(), request, response);

		}

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String op = DataUtility.getString(request.getParameter("operation"));

		UserModel userModel = new UserModel();
		RoleModel roleModel = new RoleModel();

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {

			UserBean bean = (UserBean) populateBean(request);

			try {

				bean = userModel.authenticate(bean.getLogin(), bean.getPassword());

				if (bean != null) {
					session.setAttribute("user", bean);
					RoleBean roleBean = roleModel.findByPk(bean.getId());

					if (roleBean != null) {
						session.setAttribute("role", roleBean.getName());
					}

					ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
					return;
				} else {

					bean = (UserBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Invalid credential", request);
					ServletUtility.forward(getView(), request, response);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}

}
