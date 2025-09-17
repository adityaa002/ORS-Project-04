package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.util.ServletUtility;

/**
 * Error Controller to handle system errors and forward to the error view.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
@WebServlet("/ctl/ErrorCtl")
public class ErrorCtl extends BaseCtl {

	private static Logger log  = Logger.getLogger(ErrorCtl.class);

	/**
	 * Handles GET requests and forwards to the error view.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("ErrorCtl doget method started");

		ServletUtility.forward(getView(), request, response);

		log.debug("ErrorCtl doget method ended");

	}

	/**
	 * Handles POST requests and forwards to the error view.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("ErrorCtl dopost method started");

		ServletUtility.forward(getView(), request, response);

		log.debug("ErrorCtl dopost method ended");

	}

	/**
	 * Returns the error view path.
	 * 
	 * @return ERROR_VIEW constant
	 */
	@Override
	protected String getView() {
		return ORSView.ERROR_VIEW;
	}
}
