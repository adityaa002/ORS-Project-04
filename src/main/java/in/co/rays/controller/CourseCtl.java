package in.co.rays.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * CourseCtl Controller. Performs operations for Course entity like Add, Update, Delete, and Get.
 *
 * @author Aditya

 * @version 1.0
 * @since 1.0
 */
@WebServlet(name = "CourseCtl", urlPatterns = { "/CourseCtl" })
public class CourseCtl extends BaseCtl {
	
	Logger log = Logger.getLogger(CollegeListCtl.class);


    /**
     * Loads preloaded data like course durations into request.
     *
     * @param request HttpServletRequest
     */
    @Override
    protected void preload(HttpServletRequest request) {
    	
    	log.debug("CourseCtl preload method started");
    	
        HashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("1 Year", "1 Year");
        map.put("2 Year", "2 Year");
        map.put("3 Year", "3 Year");
        map.put("4 Year", "4 Year");
        map.put("5 Year", "5 Year");
        map.put("6 Year", "6 Year");
        map.put("7 Year", "7 Year");

        request.setAttribute("map", map);
        
    	log.debug("CourseCtl preload method ended");

    }

    /**
     * Validates input data.
     *
     * @param request HttpServletRequest
     * @return true if data is valid, otherwise false
     */
    @Override
    protected boolean validate(HttpServletRequest request) {
    	
    	log.debug("CourseCtl validate method started");

    	
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("name"))) {
            request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
            pass = false;
        } else if (!DataValidator.isName(request.getParameter("name"))) {
            request.setAttribute("name", "Invalid Name");
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("duration"))) {
            request.setAttribute("duration", PropertyReader.getValue("error.require", "Duration"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("description"))) {
            request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
            pass = false;
        }
        
    	log.debug("CourseCtl validate method ended");

        return pass;
    }

    /**
     * Populates CourseBean object from request parameters.
     *
     * @param request HttpServletRequest
     * @return populated CourseBean
     */
    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
    	
    	log.debug("CourseCtl populateBean method started");

    	
        CourseBean bean = new CourseBean();

        bean.setId(DataUtility.getInt(request.getParameter("id")));
        bean.setName(DataUtility.getString(request.getParameter("name")));
        bean.setDuration(DataUtility.getString(request.getParameter("duration")));
        bean.setDescription(DataUtility.getString(request.getParameter("description")));

        populateDto(bean, request);
        
    	log.debug("CourseCtl populateBean method ended");
        return bean;
    }

    /**
     * Handles GET request.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	log.debug("CourseCtl doget method started");


        String op = DataUtility.getString(request.getParameter("operation"));
        CourseModel model = new CourseModel();
        long id = DataUtility.getLong(request.getParameter("id"));

        if (id > 0 || op != null) {
            try {
                CourseBean bean = model.findByPK(id);
                ServletUtility.setBean(bean, request);
            } catch (ApplicationException e) {
                e.printStackTrace();
            }
        }
        
    	log.debug("CourseCtl doget method ended");
        ServletUtility.forward(getView(), request, response);
        
    }

    /**
     * Handles POST request.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	log.debug("CourseCtl dopost method started");


        String op = DataUtility.getString(request.getParameter("operation"));
        CourseModel model = new CourseModel();

        if (OP_SAVE.equalsIgnoreCase(op)) {
            CourseBean bean = (CourseBean) populateBean(request);

            try {
                long pk = model.add(bean);
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Course added successfully", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Course already exists", request);
                e.printStackTrace();
            }

        } else if (OP_UPDATE.equalsIgnoreCase(op)) {
            CourseBean bean = (CourseBean) populateBean(request);

            try {
                if (bean.getId() > 0) {
                    model.update(bean);
                }
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Data is successfully updated", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Course already exists", request);
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
            return;
        } else if (OP_RESET.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
            return;
        }
        
    	log.debug("CourseCtl dopost method ended");
        ServletUtility.forward(getView(), request, response);
    }

    /**
     * Returns the view page path.
     *
     * @return String view
     */
    @Override
    protected String getView() {
        return ORSView.COURSE_VIEW;
    }
}
