/**
 * ForgetPasswordCtl servlet handles the "Forgot Password" functionality.
 * It validates the user input and initiates the process of sending a reset 
 * password to the user's registered email address.
 * 
 * @author Aditya
 * @version 1.0
 * @since 2025
 */

package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.model.UserModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * Servlet implementation class ForgetPasswordCtl
 */
@WebServlet(name = "ForgetPasswordCtl", urlPatterns = { "/ForgetPasswordCtl" })
public class ForgetPasswordCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(ForgetPasswordCtl.class);

	/**
	 * Validates the email input for forget password.
	 *
	 * @param request HttpServletRequest
	 * @return true if valid, false otherwise
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("ForgetPasswordCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Email Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Login "));
			pass = false;
		}

		log.debug("ForgetPasswordCtl validate method ended with status : " + pass);

		return pass;
	}

	/**
	 * Populates UserBean with request data.
	 *
	 * @param request HttpServletRequest
	 * @return UserBean object
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("ForgetPasswordCtl populateBean method started");

		UserBean bean = new UserBean();

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		log.debug("ForgetPasswordCtl populateBean method ended");

		return bean;
	}

	/**
	 * Handles GET request and forwards to view.
	 *
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("ForgetPasswordCtl doget method started");

		ServletUtility.forward(getView(), request, response);

		log.debug("ForgetPasswordCtl doget method ended");
	}

	/**
	 * Handles POST request for forget password operation.
	 *
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("ForgetPasswordCtl dopost method started");

		String op = DataUtility.getString(request.getParameter("operation"));

		UserBean bean = (UserBean) populateBean(request);

		UserModel model = new UserModel();

		if (OP_GO.equalsIgnoreCase(op)) {
			try {
				boolean flag = model.forgetPassword(bean.getLogin());
				if (flag) {
					ServletUtility.setSuccessMessage("Password has been sent to your email id", request);
				}
			} catch (RecordNotFoundException e) {
				ServletUtility.setErrorMessage(e.getMessage(), request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.setErrorMessage("Please check your internet connection..!!", request);
			}
			ServletUtility.forward(getView(), request, response);
		}

		log.debug("ForgetPasswordCtl dopost method ended");

	}

	/**
	 * Returns view for forget password screen.
	 *
	 * @return view path
	 */
	@Override
	protected String getView() {
		return ORSView.FORGET_PASSWORD_VIEW;
	}
}
