package in.co.rays.testModel;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.MarksheetBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.model.MarksheetModel;

public class TestMarksheetModel {

	public static void main(String[] args) throws Exception {
		// testNextPk();
		// testAdd();
		// testDelete();
		// testFindByPk();
		// testSearch();
		 testList();
		// testFindByRoll();
	}

	// public static void testFindByRoll() throws SQLException {

	/*
	 * MarksheetModel model = new MarksheetModel(); MarksheetBean bean =
	 * model.findByPk("AB123");
	 * 
	 * if (bean != null) {
	 * 
	 * System.out.print(bean.getId()); System.out.print("\t" + bean.getRollNo());
	 * System.out.print("\t" + bean.getStudentId()); System.out.print("\t" +
	 * bean.getName()); System.out.print("\t" + bean.getPhysics());
	 * System.out.print("\t" + bean.getChemistry()); System.out.print("\t" +
	 * bean.getMaths()); System.out.print("\t" + bean.getCreatedBy());
	 * System.out.print("\t" + bean.getModifiedBy()); System.out.print("\t" +
	 * bean.getCreatedDatetime()); System.out.println("\t" +
	 * bean.getModifiedDatetime());
	 * 
	 * }else { System.out.println("record not found"); }
	 * 
	 * }
	 */
	public static void testFindByPk() throws SQLException, ApplicationException {

		MarksheetModel model = new MarksheetModel();
		MarksheetBean bean = model.findByPk(1);

		if (bean != null) {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getRollNo());
			System.out.print("\t" + bean.getStudentId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getPhysics());
			System.out.print("\t" + bean.getChemistry());
			System.out.print("\t" + bean.getMaths());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("record not found");
		}

	}

	public static void testDelete() throws SQLException {
		MarksheetModel model = new MarksheetModel();
		// model.delete(2);
	}

	public static void testNextPk() throws SQLException, DatabaseException {
		MarksheetModel model = new MarksheetModel();
		int i = model.nextPk();
		System.out.println("next primary key :- " + i);
	}

	public static void testAdd() throws Exception {
		MarksheetModel model = new MarksheetModel();
		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo("ABC125");
		bean.setStudentId(1);
		bean.setPhysics(60);
		bean.setChemistry(77);
		bean.setMaths(65);
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

	/*
	 * public static void testSearch() throws SQLException { MarksheetModel model =
	 * new MarksheetModel(); MarksheetBean bean = new MarksheetBean();
	 * bean.setName("a"); List list = model.Search(bean, 1, 10);
	 * 
	 * Iterator it = list.iterator();
	 * 
	 * while (it.hasNext()) { bean = (MarksheetBean) it.next();
	 * 
	 * System.out.print(bean.getId()); System.out.print("\t" + bean.getRollNo());
	 * System.out.print("\t" + bean.getStudentId()); System.out.print("\t" +
	 * bean.getName()); System.out.print("\t" + bean.getPhysics());
	 * System.out.print("\t" + bean.getChemistry()); System.out.print("\t" +
	 * bean.getMaths()); System.out.print("\t" + bean.getCreatedBy());
	 * System.out.print("\t" + bean.getModifiedBy()); System.out.print("\t" +
	 * bean.getCreatedDatetime()); System.out.println("\t" +
	 * bean.getModifiedDatetime());
	 * 
	 * } }
	 */

	public static void testList() throws SQLException, ApplicationException {
		MarksheetModel model = new MarksheetModel();
		MarksheetBean bean = new MarksheetBean();
		List list = model.list();

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (MarksheetBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getRollNo());
			System.out.print("\t" + bean.getStudentId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getPhysics());
			System.out.print("\t" + bean.getChemistry());
			System.out.print("\t" + bean.getMaths());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}
}
