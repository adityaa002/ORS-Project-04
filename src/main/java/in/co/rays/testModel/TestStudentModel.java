package in.co.rays.testModel;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.MarksheetBean;
import in.co.rays.bean.StudentBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.model.MarksheetModel;
import in.co.rays.model.StudentModel;

public class TestStudentModel {
	
	public static void main(String[] args) throws Exception {
		 //testAdd();
				// testUpdate();
				//testDelete();
				 //testFindByPk();
				//testFindByEmail();
				//testSearch();
		testList();
	}
	
	public static void testNextPk() throws DatabaseException {
		StudentModel model = new StudentModel();
		model.nextPk();
		System.out.println(model.nextPk());
	}
	
	public static void testAdd() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StudentBean bean = new StudentBean();
		bean.setFirstName("Aman");
		bean.setLastName("Rathore");
		bean.setDob(sdf.parse("2003-04-19"));
		bean.setGender("male");
		bean.setMobileNo("8598785487");
		bean.setEmail("aman02@gmail.com");
		bean.setCollegeId(9);
 		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		StudentModel  model = new StudentModel();
		model.add(bean);
	}
	public static void testUpdate() throws Exception {

		StudentModel model = new StudentModel();

		StudentBean bean = model.findByPk(1);

		bean.setFirstName("Amay");
		bean.setEmail("amay001@gmail.com");

		model.update(bean);
	}

	public static void testDelete() throws Exception {

		StudentModel model = new StudentModel();

		//model.delete(1);

	}

	public static void testFindByPk() throws Exception {

		StudentModel model = new StudentModel();

		StudentBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("id not found");
		}
	}

	public static void testList() throws SQLException, ApplicationException {
		StudentModel model = new StudentModel();
		StudentBean bean = new StudentBean();
		List list = model.list();

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (StudentBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}

	}
	public static void testSearch() throws Exception {

		StudentBean bean = new StudentBean();
		//bean.setFirstName("a");

		StudentModel model = new StudentModel();

		List list = model.search(bean, 1, 10);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (StudentBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}
}
