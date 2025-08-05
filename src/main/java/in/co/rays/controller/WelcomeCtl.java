package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.util.ServletUtility;

@WebServlet(name = "WelcomeCtl", urlPatterns = { "/WelcomeCtl" })
public class WelcomeCtl extends BaseCtl {

	private static Logger log  = Logger.getLogger(WelcomeCtl.class);

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("WelcomeCtl doget method started");
		
		ServletUtility.forward(getView(), request, response);
		
		log.debug("WelcomeCtl doget method ended");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("Entered in WelcomeCtl dopost");

 
	}

	@Override
	protected String getView() {
		return ORSView.WELCOME_VIEW;
	}

}
