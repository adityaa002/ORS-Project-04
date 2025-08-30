package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.util.ServletUtility;

/**
 * WelcomeCtl handles the welcome page requests and forwards to the welcome view.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
@WebServlet(name = "WelcomeCtl", urlPatterns = { "/WelcomeCtl" })
public class WelcomeCtl extends BaseCtl {

	private static Logger log  = Logger.getLogger(WelcomeCtl.class);

	/**
	 * Handles GET requests and forwards to the welcome view.
	 *
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("WelcomeCtl doget method started");
		ServletUtility.forward(getView(), request, response);
		log.debug("WelcomeCtl doget method ended");
	}

	/**
	 * Handles POST requests. Currently logs entry point; no action implemented.
	 *
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("Entered in WelcomeCtl dopost");
	}

	/**
	 * Returns the view page for the welcome page.
	 *
	 * @return String representing the view path
	 */
	@Override
	protected String getView() {
		return ORSView.WELCOME_VIEW;
	}

}
