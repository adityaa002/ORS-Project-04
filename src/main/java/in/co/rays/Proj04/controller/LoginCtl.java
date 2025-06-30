package in.co.rays.Proj04.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.Proj04.bean.BaseBean;
import in.co.rays.Proj04.bean.RoleBean;
import in.co.rays.Proj04.bean.UserBean;
import in.co.rays.Proj04.exception.ApplicationException;
import in.co.rays.Proj04.model.RoleModel;
import in.co.rays.Proj04.model.UserModel;
import in.co.rays.Proj04.util.DataUtility;
import in.co.rays.Proj04.util.DataValidator;
import in.co.rays.Proj04.util.ServletUtility;

@WebServlet(name = "LoginCtl", urlPatterns = { "/LoginCtl" })
public class LoginCtl extends BaseCtl {

	public static final String OP_SIGN_IN = "SignIn";

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean isValid = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			isValid = false;
			request.setAttribute("login", "login is required");
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			isValid = false;
			request.setAttribute("password", "password is required");
		}

		return isValid;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();

		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		RoleModel rmodel = new RoleModel();
		RoleBean rbean = new RoleBean();

		HttpSession session = request.getSession();

		bean = (UserBean) populateBean(request);

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {

			System.out.println(bean.getLogin());

			try {
				bean = model.authenticate(bean.getLogin(), bean.getPassword());
				if (bean != null) {
					System.out.println("login successfully");

					session.setAttribute("user", bean);
					rbean = rmodel.findByPk(bean.getRoleId());
					session.setAttribute("role", rbean.getName());
					ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);

					
					
				} else {
					System.out.println("invalid login or password");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}

}