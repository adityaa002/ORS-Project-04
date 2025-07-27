package in.co.rays.testModel;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.UserModel;
import in.co.rays.util.DataUtility;

public class TestUserModel {

	public static void main(String[] args) throws Exception {
		// testNextPk();
		// testAdd();
		// testUpdate();
		testDelete();
		// testFindByLogin();
		// testFindByPk();
		// testAuth();
		// testSearch();
		// testListAll();
	}

	public static void testListAll() throws Exception {
		UserModel model = new UserModel();
		List<UserBean> list = model.list();
		for (UserBean bean : list) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}

	public static void testNextPk() {
		UserModel model = new UserModel();
		model.nextPk();
		System.out.println(model.nextPk());
	}

	public static void testAdd() throws ParseException, SQLException, ApplicationException, DuplicateRecordException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserBean bean = new UserBean();
		bean.setFirstName("Aditya");
		bean.setLastName("Sharma");
		bean.setLogin("aditya@gmail.com");
		bean.setPassword("adi@123");
		bean.setDob(sdf.parse("2002-07-12"));
		bean.setMobileNo("8574585965");
		bean.setRoleId(1);
		bean.setGender("male");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		UserModel model = new UserModel();
		model.add(bean);
	}

	public static void testUpdate()
			throws ParseException, SQLException, DuplicateRecordException, ApplicationException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserBean bean = new UserBean();
		bean.setId(1);
		bean.setFirstName("Amit");
		bean.setLastName("Sharma");
		bean.setLogin("aditya02@gmail.com");
		bean.setPassword("adi@1234");
		bean.setDob(sdf.parse("2002-07-12"));
		bean.setMobileNo("8574585965");
		bean.setRoleId(2);
		bean.setGender("male");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		UserModel model = new UserModel();
		model.update(bean);

	}

	public static void testDelete() throws SQLException {
		UserModel model = new UserModel();
		UserBean bean= null;
//model.delete(DataUtility.getString("bean.getId()"));
	}

	public static void testFindByLogin() throws SQLException, ApplicationException {
		UserModel model = new UserModel();

		UserBean bean = model.findByLogin("aditya02@gmail.com");

		if (bean != null) {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}

	public static void testFindByPk() throws SQLException {
		UserModel model = new UserModel();
		UserBean bean = model.findByPk(1);

		if (bean != null) {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}
	}

	public static void testAuth() throws SQLException, ApplicationException {
		UserModel model = new UserModel();
		UserBean bean = model.authenticate("aditya02@gmail.com", "adi@123");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}

	}

	public static void testSearch() throws SQLException, ApplicationException {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		// bean.setFirstName("a");
		List list = model.search(bean, 1, 2);

		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (UserBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}

}
