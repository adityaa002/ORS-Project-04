package in.co.rays.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.RoleModel;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.ServletUtility;

/**
 * RoleCtl Controller to handle add/update role operations.
 * 
 * @author Aditya
 */
@WebServlet(name = "RoleCtl", urlPatterns = { "/ctl/RoleCtl" })
public class RoleCtl extends BaseCtl {
	
	private static Logger log  = Logger.getLogger(RoleCtl.class);


    /**
     * Validates Role form input.
     * 
     * @param request HttpServletRequest object
     * @return boolean true if valid, false otherwise
     */
    @Override
    protected boolean validate(HttpServletRequest request) {
    	
		log.debug("RoleCtl validate method started");

        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("roleName"))) {
            request.setAttribute("roleName", PropertyReader.getValue("error.require", "Role Name"));
            pass = false;
        } else if (!DataValidator.isName(request.getParameter("roleName"))) {
            request.setAttribute("roleName", "Invalid Role name");
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("description"))) {
            request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
            pass = false;
        }
        
		log.debug("RoleCtl validate method ended with status : "+ pass);

        return pass;
    }

    /**
     * Populates RoleBean from request parameters.
     * 
     * @param request HttpServletRequest object
     * @return populated RoleBean
     */
    @Override
    protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("RoleCtl populateBean method started");

    	
        RoleBean bean = new RoleBean();
        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setName(DataUtility.getString(request.getParameter("roleName")));
        bean.setDescription(DataUtility.getString(request.getParameter("description")));

        populateDto(bean, request);
		log.debug("RoleCtl populateBean method ended");

        return bean;
    }

    /**
     * Handles HTTP GET requests. Loads Role data for editing.
     * 
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
		log.debug("RoleCtl doget method started");


        String op = DataUtility.getString(request.getParameter("operation"));

        RoleModel model = new RoleModel();

        long id = DataUtility.getLong(request.getParameter("id"));

        if (id > 0 || op != null) {
            RoleBean bean;
            try {
                bean = model.findByPk(id);
                ServletUtility.setBean(bean, request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                return;
            }
        }

        ServletUtility.forward(getView(), request, response);
        
		log.debug("RoleCtl doget method ended");

    }

    /**
     * Handles HTTP POST requests. Performs Save, Update, Cancel, and Reset operations.
     * 
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
		log.debug("RoleCtl dopost method started");


        String op = DataUtility.getString(request.getParameter("operation"));

        RoleModel model = new RoleModel();

        if (OP_SAVE.equalsIgnoreCase(op)) {
            RoleBean bean = (RoleBean) populateBean(request);
            try {
                long pk = model.add(bean);
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Role Added Successfully..!", request);

            } catch (ApplicationException e) {
                e.printStackTrace();
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Role already exists..!", request);
                e.printStackTrace();
            }
        } else if (OP_UPDATE.equalsIgnoreCase(op)) {
            RoleBean bean = (RoleBean) populateBean(request);

            try {
                if (bean.getId() > 0) {
                    System.out.println("in update");
                    model.update(bean);
                }
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Role Updated successfully", request);

            } catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Role already exist", request);
                e.printStackTrace();
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
            return;
        } else if (OP_RESET.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
            return;
        }

        ServletUtility.forward(getView(), request, response);
        
		log.debug("RoleCtl dopost method ended");

    }

    /**
     * Returns the view page for Role.
     * 
     * @return String path of ROLE_VIEW
     */
    @Override
    protected String getView() {
        return ORSView.ROLE_VIEW;
    }

}
