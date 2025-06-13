package in.co.rays.Proj04.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.Proj04.bean.RoleBean;
import in.co.rays.Proj04.model.RoleModel;

public class TestRoleModel {

	public static void main(String[] args) throws Exception {
		// testNextpk();
		// testAdd();
		// testDelete();
		testUpdate();
	}

	public static void testNextpk() {
		RoleModel model = new RoleModel();
		int i = model.nextpk();
		System.out.println("nextPk : " + i);
	}

	public static void testAdd() throws SQLException {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();

		bean.setName("admin");
		bean.setDescription("admin has full access of application");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}

	public static void testDelete() throws Exception {
		RoleModel model = new RoleModel();
		model.delete(1);
	}

	public static void testUpdate() throws Exception {
		RoleModel model = new RoleModel();
		RoleBean bean = new RoleBean();

		bean.setName("new admin");
		bean.setDescription("admin can control everything");
		bean.setCreatedBy("another root");
		bean.setModifiedBy("first admin");
 		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		bean.setId(1);
		model.update(bean);

	}
}
