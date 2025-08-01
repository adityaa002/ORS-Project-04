package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.MarksheetBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.MarksheetModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * Controller to get a student's marksheet by roll number.
 *
 * @author Aditya
 */
@WebServlet(name = "GetMarksheetCtl", urlPatterns = { "/GetMarksheetCtl" })
public class GetMarksheetCtl extends BaseCtl {

    /**
     * Validates the input data from request.
     *
     * @param request the HttpServletRequest object
     * @return boolean indicating whether the input is valid
     */
    @Override
    protected boolean validate(HttpServletRequest request) {

        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("rollNo"))) {
            request.setAttribute("rollNo", PropertyReader.getValue("error.require", "Roll Number"));
            pass = false;
        }

        return pass;
    }

    /**
     * Populates a MarksheetBean object from the request parameters.
     *
     * @param request the HttpServletRequest object
     * @return populated MarksheetBean
     */
    @Override
    protected BaseBean populateBean(HttpServletRequest request) {

        MarksheetBean bean = new MarksheetBean();

        bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

        return bean;
    }

    /**
     * Handles GET requests and forwards to the appropriate view.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletUtility.forward(getView(), request, response);
    }

    /**
     * Handles POST requests for retrieving the marksheet based on roll number.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = DataUtility.getString(request.getParameter("operation"));

        MarksheetModel model = new MarksheetModel();

        MarksheetBean bean = (MarksheetBean) populateBean(request);

        if (OP_GO.equalsIgnoreCase(op)) {
            try {
                bean = model.findByRollNo(bean.getRollNo());
                if (bean != null) {
                    ServletUtility.setBean(bean, request);
                } else {
                    ServletUtility.setErrorMessage("RollNo Does Not exists", request);
                }
            } catch (ApplicationException e) {
                e.printStackTrace();
                //ServletUtility.handleException(e, request, response);
                return;
            }
        }
        ServletUtility.forward(getView(), request, response);
    }

    /**
     * Returns the view page for this controller.
     *
     * @return the view name
     */
    @Override
    protected String getView() {
        return ORSView.GET_MARKSHEET_VIEW;
    }
}
