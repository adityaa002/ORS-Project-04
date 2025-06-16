package in.co.rays.Proj04.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.rays.Proj04.bean.UserBean;
import in.co.rays.Proj04.model.UserModel;

public class TestUserModel {
	public static void main(String[] args) throws Exception {
		// testNextpk();
		// testAdd();
		//testUpdate();
		
	}

	public static void testNextpk() {

		UserModel model = new UserModel();
		model.nextpk();
	}

	public static void testAdd() throws Exception {
		UserBean bean = new UserBean();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bean.setFirstName("aditya");
		bean.setLastName("sharma");
		bean.setLogin("aditya02");
		bean.setPassword("pass@12");
		try {
			bean.setDob(sdf.parse("2002-07-26"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		bean.setMobileNo("7854569565");
		bean.setRoleId(13254);
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

		bean.setFirstName("aditya");
		bean.setLastName("sharma");
		bean.setLogin("aditya002@");
		bean.setPassword("furi@23");
		bean.setDob(sdf.parse("2002-03-17"));
		bean.setMobileNo("5685745854");
		bean.setRoleId(564577);
		bean.setGender("male");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("root");
		bean.setId(1);

		model.update(bean);
	}
}
