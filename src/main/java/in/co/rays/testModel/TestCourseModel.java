package in.co.rays.testModel;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CourseBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModel;

public class TestCourseModel {

	public static void main(String[] args) throws Exception {
		// testNextPk();
		// testAdd();
		// testUpdate();
		//testDelete();
		// testFindByPk();
		// testFindByName();
		// testSearch();
		//testList();

	}

	public static void testList() throws SQLException, ApplicationException {
		CourseModel model = new CourseModel();
		CourseBean bean = new CourseBean();
		List list = model.list();
		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (CourseBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}

	public static void testSearch() throws SQLException, ApplicationException {

		CourseModel model = new CourseModel();
		CourseBean bean = new CourseBean();
		//bean.setName("b");
		List list = model.search(bean, 1, 15);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (CourseBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}

	public static void testFindByName() throws SQLException, ApplicationException {
		CourseModel model = new CourseModel();
		CourseBean bean = model.findByName("B Design");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("course did't exist");
		}
	}

	public static void testFindByPk() throws SQLException, ApplicationException {
		CourseModel model = new CourseModel();
		CourseBean bean = model.findByPK(1);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("id not found");
		}

	}

	public static void testUpdate() throws SQLException, ApplicationException, DuplicateRecordException {
		CourseModel model = new CourseModel();
		CourseBean bean = new CourseBean();
		bean.setName("B Tech");
		bean.setDuration("4 years");
		bean.setDescription("Bachelors of Technology");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(1);
		model.update(bean);
	}

	public static void testNextPk() throws DatabaseException {
		CourseModel model = new CourseModel();
		int i = model.nextPK();
		System.out.println("next primary key : " + i);
	}

	public static void testAdd() throws SQLException, ApplicationException, DuplicateRecordException {

		CourseModel model = new CourseModel();
		CourseBean bean = new CourseBean();

		bean.setName("B Design");
		bean.setDuration("4 years");
		bean.setDescription("Bachelors of Designing");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}

	public static void testDelete() throws Exception {

		CourseModel model = new CourseModel();

		//model.delete(15);

	}

}
