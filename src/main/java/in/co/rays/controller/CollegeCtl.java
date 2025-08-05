package in.co.rays.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CollegeBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CollegeModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

 
@WebServlet(name = "CollegeCtl", urlPatterns = { "/ctl/CollegeCtl" })
public class CollegeCtl extends BaseCtl {

	private static Logger log  = Logger.getLogger(CollegeCtl.class);

    
    @Override
    protected boolean validate(HttpServletRequest request) {
    	
    	log.debug("ChangePasswordCtl validate method started");

    	
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("name"))) {
            request.setAttribute("name", PropertyReader.getValue("error.require", "College Name"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("address"))) {
            request.setAttribute("address", PropertyReader.getValue("error.require", "College Address"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("state"))) {
            request.setAttribute("state", PropertyReader.getValue("error.require", "College State"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("city"))) {
            request.setAttribute("city", PropertyReader.getValue("error.require", "College City"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("phoneNo"))) {
            request.setAttribute("phoneNo", PropertyReader.getValue("error.require", "Phone no."));
            pass = false;
        } else if (DataValidator.isPhoneNo(request.getParameter("phoneNo"))
                && DataValidator.isPhoneLength(request.getParameter("phoneNo"))) {
            request.setAttribute("phoneNo", "Enter valid Phone No");
            pass = false;
        }

    	log.debug("CollegeCtl validate method ended  with status : "+pass);

        return pass;
    }

     
    @Override
    protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("CollegeCtl Method populatebean Started");

    	
        CollegeBean bean = new CollegeBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setName(DataUtility.getString(request.getParameter("name")));
        bean.setAddress(DataUtility.getString(request.getParameter("address")));
        bean.setState(DataUtility.getString(request.getParameter("state")));
        bean.setCity(DataUtility.getString(request.getParameter("city")));
        bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));

        populateDto(bean, request);

		log.debug("CollegeCtl Method populatebean Ended");
        return bean;
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
		log.debug("CollegeCtl Method doget Started");


        String op = DataUtility.getString(request.getParameter("operation"));

        CollegeModel model = new CollegeModel();

        long id = DataUtility.getLong(request.getParameter("id"));

        if (id > 0 || op != null) {
            CollegeBean bean;
            try {
                bean = model.findByPK(id);
                ServletUtility.setBean(bean, request);
            } catch (Exception e) {
            	log.error(e);
                e.printStackTrace();
                return;
            }
        }

        ServletUtility.forward(getView(), request, response);
		log.debug("CollegeCtl Method doget ended");

    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
		log.debug("CollegeCtl Method doPost Started");


        String op = DataUtility.getString(request.getParameter("operation"));

        CollegeModel model = new CollegeModel();

        System.out.println(op);

        if (OP_SAVE.equalsIgnoreCase(op)) {
            CollegeBean bean = (CollegeBean) populateBean(request);
            try {
                long pk = model.add(bean);
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("College added successfully..!", request);
            } catch (ApplicationException e) {
            	log.error(e);
                e.printStackTrace();
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("College already exists..!", request);
                e.printStackTrace();
            }

        } else if (OP_UPDATE.equalsIgnoreCase(op)) {
            CollegeBean bean = (CollegeBean) populateBean(request);
            try {
                if (bean.getId() > 0) {
                    model.update(bean);
                }
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("College updated successfully", request);
            } catch (ApplicationException e) {
            	log.error(e);
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("College name already exist..!", request);
            } catch (DuplicateRecordException e) {
                e.printStackTrace();
            }

        } else if (OP_RESET.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.COLLEGE_CTL, request, response);
            return;

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
            return;
        }

        ServletUtility.forward(getView(), request, response);


		log.debug("CollegeCtl Method doPost ended");
}

    
    @Override
    protected String getView() {
        return ORSView.COLLEGE_VIEW;
    }
}
