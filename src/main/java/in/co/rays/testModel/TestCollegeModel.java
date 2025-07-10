package in.co.rays.testModel;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.bean.UserBean;
import in.co.rays.model.CollegeModel;
import in.co.rays.model.RoleModel;
import in.co.rays.model.UserModel;

public class TestCollegeModel {
	public static void main(String[] args) throws Exception {
		// testAdd();
		// testPk();
		// testUpdate();
		// testDelete();
		// testFindByName();
		// testFindByPk();
		// testSearch();
		testListAll();
	}

	public static void testListAll() throws Exception {
		CollegeModel model = new CollegeModel();
		List<CollegeBean> list = model.list();
		for (CollegeBean bean : list) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}

	private static void testSearch() throws SQLException {

		CollegeModel model = new CollegeModel();
		CollegeBean bean = new CollegeBean();
		// bean.setName("s");
		List list = model.search(bean, 1, 20);

		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (CollegeBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}

	public static void testFindByName() throws SQLException {
		CollegeModel model = new CollegeModel();
		CollegeBean bean = model.findByName("Indian Institute of Science Education and Research");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}

	}

	public static void testPk() {
		CollegeModel model = new CollegeModel();
		model.nextPk();
		System.out.println(model.nextPk());
	}

	public static void testAdd() throws SQLException {
		CollegeModel model = new CollegeModel();
		CollegeBean bean = new CollegeBean();
		bean.setName("Sagar Institute of Research and Technology");
		bean.setAddress("Ayodhya Bypass");
		bean.setState("MadhyaPradesh");
		bean.setCity("Bhopal");
		bean.setPhoneNo("7854569854");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}

	public static void testUpdate() throws SQLException {

		CollegeModel model = new CollegeModel();
		CollegeBean bean = new CollegeBean();
		bean.setName("Sagar Institute of Research  and Technology");
		bean.setAddress("Ayodhya Bypass road");
		bean.setState("MadhyaPradesh");
		bean.setCity("Bhopal");
		bean.setPhoneNo("9658745213");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(1);
		model.update(bean);

	}

	public static void testDelete() throws SQLException {
		CollegeModel model = new CollegeModel();
		CollegeBean bean  = new CollegeBean();
		bean.setId(1);
		model.delete(bean);
	}

	public static void testFindByPk() throws SQLException {
		CollegeModel model = new CollegeModel();
		CollegeBean bean = model.findByPk(4);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}

	}
}
