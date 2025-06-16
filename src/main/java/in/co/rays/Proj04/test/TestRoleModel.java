package in.co.rays.Proj04.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.Proj04.bean.RoleBean;
import in.co.rays.Proj04.bean.UserBean;
import in.co.rays.Proj04.model.RoleModel;

public class TestRoleModel {

	public static void main(String[] args) throws Exception {
		// testNextpk();
		//testAdd();
		// testDelete();
		// testUpdate();
		testSearch();
	}

	public static void testNextpk() {
		RoleModel model = new RoleModel();
		int i = model.nextpk();
		System.out.println("nextPk : " + i);
	}

	public static void testAdd() throws SQLException {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();

		bean.setName("kiosk");
		bean.setDescription("kiosk");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("root");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}

	public static void testDelete() throws Exception {
		RoleModel model = new RoleModel();
		RoleBean bean = new RoleBean();
		bean.setId(6);
		model.delete(bean);
	}

	public static void testUpdate() throws Exception {
		RoleModel model = new RoleModel();
		RoleBean bean = new RoleBean();

		bean.setName("admin");
		bean.setDescription("admin has full access of application");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");

		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		bean.setId(1);
		model.update(bean);

	}

	public static void testSearch() {
		RoleBean bean = new RoleBean();
		
		bean.setName("admin");
		RoleModel model = new RoleModel();
 
 		
		List list = model.search(bean);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (RoleBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());
 
		}

	}
	
	public void testFindByPk() {
		
	}
}
