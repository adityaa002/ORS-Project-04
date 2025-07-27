package in.co.rays.testModel;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.RoleBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.RoleModel;

public class TestRoleModel {

	public static void testNextPk() throws SQLException {
		RoleModel model = new RoleModel();
		int i = model.nextPk();
		System.out.println(i);
	}

	public static void testAdd() throws SQLException, DuplicateRecordException, ApplicationException {
		
		RoleBean bean = new RoleBean();
		bean.setName("college");
		bean.setDescription("college");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmial.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		RoleModel model = new RoleModel();
		model.add(bean);

	}

	public static void testUpdate() throws SQLException, DuplicateRecordException, ApplicationException {
		RoleBean bean = new RoleBean();
		bean.setName("faculty");
		bean.setDescription("Faculty");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setId(5);
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		RoleModel model = new RoleModel();
		model.update(bean);
	}

	public static void testDelete() throws SQLException {

		RoleModel model = new RoleModel();
		//model.delete(3);

	}

	public static void testFindByPk() throws SQLException, ApplicationException {
		RoleModel model = new RoleModel();
		RoleBean bean = model.findByPk(3);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			System.out.println("record not found");
		}

	}

	public static void testFindByName() throws SQLException, ApplicationException {
		RoleModel model = new RoleModel();
		RoleBean bean = model.findByName("admin");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("record not found");
		}

	}

	public static void testSearch() throws Exception {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();
		List list = model.search(bean, 1, 10);

		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (RoleBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}

	}

	public static void testListAll() throws Exception {
		RoleModel model = new RoleModel();
		List<RoleBean> list = model.list();
		for (RoleBean bean : list) {
			System.out.print("\t" + bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}
	

	public static void main(String[] args) throws Exception {

		// testNextPk();
		 // testAdd();
		 testUpdate();
		// testDelete();
		// testFindByPk();
		// testFindByName();
		// testSearch();
		// testListAll();
	}
}
