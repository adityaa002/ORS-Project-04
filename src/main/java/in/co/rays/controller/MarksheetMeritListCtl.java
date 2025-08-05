package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.MarksheetBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.MarksheetModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * MarksheetMeritListCtl servlet handles the display of top merit list of marksheets.
 * It interacts with the MarksheetModel to retrieve the data and forwards it to the view.
 *
 * @author Aditya
 */
@WebServlet(name = "MarksheetMeritListCtl", urlPatterns = { "/ctl/MarksheetMeritListCtl" })
public class MarksheetMeritListCtl extends BaseCtl {
	
	private static Logger log  = Logger.getLogger(MarksheetMeritListCtl.class);


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("MarksheetMeritListCtl doget method started");


		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		MarksheetModel model = new MarksheetModel();

		try {
			List<MarksheetBean> list = model.getMeritList(pageNo, pageSize);

			if (list == null || list.isEmpty()) {
				ServletUtility.setErrorMessage("No record found", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);

			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("MarksheetMeritListCtl doget method ended");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("MarksheetMeritListCtl dopost method started");


		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_BACK.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
			return;
		}
		
		log.debug("MarksheetMeritListCtl dopost method ended");

	}

	@Override
	protected String getView() {
		return ORSView.MARKSHEET_MERIT_LIST_VIEW;
	}
}