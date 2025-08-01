package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@WebServlet(name = "MarksheetMeritListCtl", urlPatterns = { "/MarksheetMeritListCtl" })
public class MarksheetMeritListCtl extends BaseCtl {

    /**
     * Handles HTTP GET requests.
     * Retrieves a list of top-performing students' marksheets and displays it.
     *
     * @param request  HttpServletRequest object that contains the request the client has made
     * @param response HttpServletResponse object that contains the response the servlet sends
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
            // ServletUtility.handleException(e, request, response);
            return;
        }
    }

    /**
     * Handles HTTP POST requests.
     * Primarily processes the back operation to return to the welcome view.
     *
     * @param request  HttpServletRequest object that contains the request the client has made
     * @param response HttpServletResponse object that contains the response the servlet sends
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = DataUtility.getString(request.getParameter("operation"));

        if (OP_BACK.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
            return;
        }
    }

    /**
     * Returns the view for the merit list.
     *
     * @return String representing the view path
     */
    @Override
    protected String getView() {
        return ORSView.MARKSHEET_MERIT_LIST_VIEW;
    }
}
