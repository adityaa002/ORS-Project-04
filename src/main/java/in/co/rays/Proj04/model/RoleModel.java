package in.co.rays.Proj04.model;

import java.beans.beancontext.BeanContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.Proj04.bean.RoleBean;
import in.co.rays.Proj04.bean.UserBean;
import in.co.rays.Proj04.util.JDBCDataSource;

public class RoleModel {

	public int nextpk() {

		int pk = 0;

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_role");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
				System.out.println("max id: " + pk);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return pk + 1;
	}

	public int add(RoleBean bean) throws SQLException {
		Connection conn = null;
		int id = nextpk();

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_role values( ?,?,?,?,?,?,? )");

			pstmt.setInt(1, id);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDateTime());
			pstmt.setTimestamp(7, bean.getModifiedDateTime());

			int i = pstmt.executeUpdate();

			conn.commit();
			System.out.println("data inserted successfully....!  Rows affected : " + i);

		} catch (Exception e) {
			conn.rollback();

		} finally {
			conn.close();
		}
		return id;

	}

	public void delete(int id) throws Exception {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_role where id = ?");
			pstmt.setLong(1, id);

			int i = pstmt.executeUpdate();

			conn.commit();
			System.out.println("data deleted successfully....!  Rows affected : " + i);

		} catch (Exception e) {
			conn.rollback();

		} finally {
			conn.close();
		}

	}

	public void update(RoleBean bean) throws Exception {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_role set name = ?, description=?,created_by = ?, modified_by = ? ,  modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getCreatedBy());
			pstmt.setString(4, bean.getModifiedBy());
 			pstmt.setTimestamp(5, bean.getModifiedDateTime());
			pstmt.setLong(6, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			System.out.println("record update success...!  rows affected :" + i);

		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.close();
		}

	}

}
