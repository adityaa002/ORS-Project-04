package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.util.ServletUtility;

@WebServlet("/ErrorCtl")
public class ErrorCtl extends BaseCtl {

	Logger log = Logger.getLogger(ErrorCtl.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("ErrorCtl doget method started");

		ServletUtility.forward(getView(), request, response);

		log.debug("ErrorCtl doget method ended");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("ErrorCtl dopost method started");

		ServletUtility.forward(getView(), request, response);

		log.debug("ErrorCtl dopost method ended");

	}

	@Override
	protected String getView() {
		return ORSView.ERROR_VIEW;
	}
}