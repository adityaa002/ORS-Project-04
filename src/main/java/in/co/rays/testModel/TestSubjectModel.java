package in.co.rays.testModel;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.SubjectBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.SubjectModel;

public class TestSubjectModel {

	public static void main(String[] args) throws Exception {
		// testNextPk();
		// testAdd();
		  //testDelete();
		// testUpdate();
		// testFindByPk();
		// testFindByName();
		//testSearch();
		testList();
	}

	public static void testDelete() throws SQLException {
		SubjectModel model = new SubjectModel();
		//model.delete(1);
	}

	public static void testNextPk() throws DatabaseException {
		SubjectModel model = new SubjectModel();
		int pk = model.nextPK();
		System.out.println("next primary key : " + pk);
	}

	public static void testAdd() throws SQLException, ApplicationException, DuplicateRecordException {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = new SubjectBean();

		bean.setName("Mathematics 2");
		bean.setCourseId(1);
		bean.setDescription("all  tech subjects included");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

	public static void testUpdate() throws Exception {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = model.findByPK(1);

		bean.setCourseId(7);
		model.update(bean);
	}

	public static void testFindByPk() throws Exception {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = model.findByPK(1);

		if (bean != null) {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("enter valid id...!");
		}

	}

	public static void testFindByName() throws Exception {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = model.findByName("Mathematics 2");

		if (bean != null) {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("enter valid name...!");
		}

	}

	public static void testSearch() throws Exception {

		SubjectBean bean = new SubjectBean();
		// bean.setFirstName("a");

		SubjectModel model = new SubjectModel();

		List list = model.search(bean, 1, 10);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (SubjectBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}

	public static void testList() throws Exception {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = new SubjectBean();
		List list = model.list();

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (SubjectBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}
	}

}
