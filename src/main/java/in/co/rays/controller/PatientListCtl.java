package in.co.rays.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.PatientBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.PatientModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "PatientListCtl", urlPatterns = { "/ctl/PatientListCtl" })
public class PatientListCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {

		try {

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("Male", "Male");
			map.put("Female", "Female");

			request.setAttribute("genderMap", map);

			PatientModel model = new PatientModel();
			List<PatientBean> diseaseList = (List<PatientBean>) model.list();
			request.setAttribute("diseaseList", diseaseList);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		PatientBean bean = new PatientBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setMobile(DataUtility.getString(request.getParameter("mobile")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setDisease(DataUtility.getString(request.getParameter("disease")));

		return populateDto(bean, request);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		PatientModel model = new PatientModel();
		try {
			PatientBean bean = (PatientBean) populateBean(request);
			List<PatientBean> list = model.search(bean, pageNo, pageSize);
			List<PatientBean> next = model.search(bean, pageNo + 1, pageSize);

			if (list == null || list.isEmpty()) {
				ServletUtility.setErrorMessage("No record found..!", request);
			}

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			ServletUtility.setList(list, request);

			request.setAttribute("nextListSize", next.size());

			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List list = null;
		List next = null;

		String op = DataUtility.getString(request.getParameter("operation"));

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		String[] ids = request.getParameterValues("ids");

		PatientModel model = new PatientModel();
		PatientBean bean = (PatientBean) populateBean(request);

		try {
			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {
				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				}
				if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				}
				if (OP_PREVIOUS.equalsIgnoreCase(op)) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.PATIENT_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					PatientBean deleteBean = new PatientBean();
					for (String id : ids) {
						deleteBean.setId(DataUtility.getLong(id));
						model.delete(deleteBean);
						ServletUtility.setSuccessMessage("Data is deleted Successfully...!", request);
					}

				} else {
					ServletUtility.setErrorMessage("Select atleast One Record...!", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.PATIENT_LIST_CTL, request, response);
				return;
			} else if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.PATIENT_LIST_CTL, request, response);
				return;
			}

			list = model.search(bean, pageNo, pageSize);
			next = model.search(bean, pageNo + 1, pageSize);

			if (list == null || list.size() == 0) {

				ServletUtility.setErrorMessage("No record found ..!", request);

			}

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			ServletUtility.setList(list, request);

			request.setAttribute("nextListSize", next.size());

			ServletUtility.forward(getView(), request, response);
		} catch (Exception e) {
		}
	}

	@Override
	protected String getView() {
		return ORSView.PATIENT_LIST_VIEW;
	}

}
