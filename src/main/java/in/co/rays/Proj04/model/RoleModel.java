package in.co.rays.Proj04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.Proj04.bean.RoleBean;
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

	public void delete(RoleBean bean) throws Exception {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_role where id = ?");
			pstmt.setLong(1, bean.getId());

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
					"update st_role set name = ?, description=?,created_by = ?, modified_by = ? ,  created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getCreatedBy());
			pstmt.setString(4, bean.getModifiedBy());
			pstmt.setTimestamp(5, bean.getCreatedDateTime());
			pstmt.setTimestamp(6, bean.getModifiedDateTime());
			pstmt.setLong(7, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			System.out.println("record update success...!  rows affected :" + i);

		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public RoleBean findByPk(int id) {

		Connection conn = null;
		RoleBean bean = null;

		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_role where id = ?");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDateTime(rs.getTimestamp(6));
				bean.setModifiedDateTime(rs.getTimestamp(7));

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return bean;

	}

	public List search(RoleBean bean) {

		Connection conn = null;
		List list = new ArrayList();

		try {

			conn = JDBCDataSource.getConnection();
			StringBuffer sql = new StringBuffer("select * from st_role where 1=1");

			if (bean != null) {

				if (bean.getName() != null && bean.getName().length() > 0) {
					sql.append(" and name like '" + bean.getName() + "%'");

				}
				if (bean.getDescription() != null && bean.getDescription().length() > 0) {
					sql.append(" and description like '" + bean.getDescription() + "%'");
				}
				if (bean.getCreatedBy() != null && bean.getCreatedBy().length() > 0) {
					sql.append(" and created_by like '" + bean.getCreatedBy() + "%'");
				}
				if (bean.getModifiedBy() != null && bean.getModifiedBy().length() > 0) {
					sql.append(" and modified_by like '" + bean.getModifiedBy() + "%'");
				}
				/*
				 * if(bean.getCreatedDateTime() != null) {
				 * sql.append(" and DATE(created_datetime) '"+bean.getCreatedDateTime()+"%'"); }
				 * 
				 * if(bean.getModifiedDateTime() != null) {
				 * sql.append(" and DATE(modified_datetime)" +bean.getModifiedDateTime()); }
				 */
			}
			System.out.println("your query : " + sql.toString());
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDateTime(rs.getTimestamp(6));
				bean.setModifiedDateTime(rs.getTimestamp(7));

				list.add(bean);

			}

		} catch (Exception e) {
			e.getMessage();
		}
		return list;

	}
}