package in.co.rays.Proj04.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.Proj04.bean.RoleBean;
import in.co.rays.Proj04.bean.UserBean;
import in.co.rays.Proj04.model.RoleModel;
import in.co.rays.Proj04.model.UserModel;

public class TestUserModel {
	public static void main(String[] args) throws Exception {
		// testNextpk();
		testAdd();
		// testUpdate();
		// testDelete();
		// testSearch();
		// testFindByPk();
		// testFindByLogin();
		// testAuth();
	}

	private static void testFindByPk() {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		bean = model.findByPk(3);
		System.out.print(bean.getId());
		System.out.print(" \t" + bean.getFirstName());
		System.out.print(" \t" + bean.getLastName());
		System.out.print(" \t" + bean.getLogin());
		System.out.print(" \t" + bean.getPassword());
		System.out.print(" \t" + bean.getDob());
		System.out.print(" \t" + bean.getMobileNo());
		System.out.print(" \t" + bean.getRoleId());
		System.out.print(" \t" + bean.getGender());
		System.out.print(" \t" + bean.getCreatedBy());
		System.out.print(" \t" + bean.getModifiedBy());
		System.out.print(" \t" + bean.getCreatedDateTime());
		System.out.println(" \t" + bean.getModifiedDateTime());

	}

	public static void testFindByLogin() throws SQLException {

		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		bean = model.findByLogin("shreytiwari44@gmail.com");

		System.out.print(bean.getId());
		System.out.print(" \t" + bean.getFirstName());
		System.out.print(" \t" + bean.getLastName());
		System.out.print(" \t" + bean.getLogin());
		System.out.print(" \t" + bean.getPassword());
		System.out.print(" \t" + bean.getDob());
		System.out.print(" \t" + bean.getMobileNo());
		System.out.print(" \t" + bean.getRoleId());
		System.out.print(" \t" + bean.getGender());
		System.out.print(" \t" + bean.getCreatedBy());
		System.out.print(" \t" + bean.getModifiedBy());
		System.out.print(" \t" + bean.getCreatedDateTime());
		System.out.println(" \t" + bean.getModifiedDateTime());

	}

	public static void testNextpk() {

		UserModel model = new UserModel();
		model.nextpk();
	}

	public static void testAdd() throws Exception {
		UserBean bean = new UserBean();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bean.setFirstName("kamlesh");
		bean.setLastName("singh");
		bean.setLogin("kamlesh12@gmail.com");
		bean.setPassword("pass");
		try {
			bean.setDob(sdf.parse("2000-09-28"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		bean.setMobileNo("7656365485");
		bean.setRoleId(2);
		bean.setGender("male");
		bean.setCreatedBy("root");
		bean.setModifiedBy("admin");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));

		UserModel model = new UserModel();
		model.add(bean);
	}

	public static void testUpdate() throws Exception {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstName("anvesh");
		bean.setLastName("tiwari");
		bean.setLogin("anveshtiw12@gmail.com");
		bean.setPassword("tiwari#@12");
		bean.setDob(sdf.parse("2001-01-23"));
		bean.setMobileNo("9265387845");
		bean.setRoleId(2);
		bean.setGender("male");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("root");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		bean.setId(4);

		model.update(bean);
	}

	public static void testDelete() throws Exception {
		UserBean bean = new UserBean();

		UserModel model = new UserModel();

		bean.setId(2);
		model.delete(bean);
	}

	public static void testSearch() throws Exception {

		UserBean bean = new UserBean();
		// bean.setFirstName("aditya");
		// bean.setId(2);
		// bean.setLogin("abhishah@gmail.com");

		UserModel model = new UserModel();
		List list = model.search(bean);

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
			System.out.print("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());

		}

	}

	public static void testAuth() {

		UserModel model = new UserModel();
		UserBean bean = model.authenticate("aditya02@gmail.com", "pass@12");
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
		System.out.print("\t" + bean.getCreatedDateTime());
		System.out.println("\t" + bean.getModifiedDateTime());

	}
}
