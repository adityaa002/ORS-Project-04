package in.co.rays.Proj04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import in.co.rays.Proj04.bean.UserBean;
import in.co.rays.Proj04.util.JDBCDataSource;

public class UserModel {
	public int nextpk() {

		int pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_user");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
				System.out.println("max id :" + pk);
			}

		} catch (Exception e) {

		}
		return pk + 1;

	}

	public int add(UserBean bean) throws Exception {

		int id = nextpk();
		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("insert into st_user values ( ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? )");
			pstmt.setLong(1, id);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setLong(8, bean.getRoleId());
			pstmt.setString(9, bean.getGender());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDateTime());
			pstmt.setTimestamp(13, bean.getModifiedDateTime());

			int i = pstmt.executeUpdate();
			System.out.println("Record inserted : " + i);

			conn.commit();

		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.close();
		}
		return id;

	}

	public void update(UserBean bean) throws Exception {

		int id = nextpk();
		Connection conn = null;

		try {
			System.out.println("in try");

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_user set first_name = ?, last_name = ? , login = ? , password = ? , dob = ?,mobile_no = ?, role_id = ?, gender = ?, created_by =? , modified_by = ?, created_datetime = ? ,  modified_datetime = ? where id =?");
			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setLong(7, bean.getRoleId());
			pstmt.setString(8, bean.getGender());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDateTime());
			pstmt.setTimestamp(12, bean.getModifiedDateTime());

			pstmt.setLong(13, bean.getId());

			int i = pstmt.executeUpdate();
			System.out.println("Record updated successfully : " + i);

			conn.commit();

		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public void delete(UserBean bean) {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_user where id = ?");
			pstmt.setLong(1, bean.getId());

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
