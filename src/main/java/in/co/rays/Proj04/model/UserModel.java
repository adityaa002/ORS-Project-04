package in.co.rays.Proj04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import in.co.rays.Proj04.bean.RoleBean;
import in.co.rays.Proj04.bean.UserBean;
import in.co.rays.Proj04.exception.DuplicateRecordException;
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
				// System.out.println("max id :" + pk);
			}

		} catch (Exception e) {

		}
		return pk + 1;

	}

	public int add(UserBean bean) throws Exception {

		int id = nextpk();
		Connection conn = null;
		UserBean existBean = findByLogin(bean.getLogin());

		if (existBean != null) {
			throw new DuplicateRecordException("record already exists");

		}

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

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_user set first_name = ?, last_name = ? , login = ? , password = ? , dob = ?,mobile_no = ?, role_id = ?, gender = ?, created_by =? , modified_by = ?, created_datetime = ? ,  modified_datetime = ? where id =?");
			System.out.println("in try");
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

	public void delete(UserBean bean) throws Exception {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_user where id = ?");
			pstmt.setLong(1, bean.getId());

			int i = pstmt.executeUpdate();

			conn.commit();
			System.out.println("record delete success...!!   rows affected  " + i);

		} catch (Exception e) {
			e.getMessage();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public UserBean findByPk(int id) {

		Connection conn = null;
		UserBean bean = null;

		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_user where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new UserBean();

				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getInt(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));

			}

		} catch (Exception e) {

		}

		return bean;

	}

	public UserBean findByLogin(String login) throws SQLException {
		Connection conn = null;
		UserBean bean = null;

		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login =? ");
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getInt(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return bean;

	}

	public UserBean authenticate(String login, String password) {
		Connection conn = null;
		UserBean bean = null;

		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login = ? and password = ?");
			pstmt.setString(1, login);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new UserBean();

				bean.setId(rs.getInt(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getInt(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return bean;

	}

	public List search(UserBean bean) throws Exception {

		Connection conn = null;
		List list = new ArrayList();

		try {

			conn = JDBCDataSource.getConnection();

			StringBuffer sql = new StringBuffer("select * from st_user where 1=1");

			if (bean != null) {
				if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
					sql.append(" and first_name like '" + bean.getFirstName() + "%'");
				}
				if (bean.getId() != 0 && bean.getId() > 0) {
					sql.append(" and id like '" + bean.getId() + "%'");
				}
				if (bean.getLogin() != null && bean.getLogin().length() > 0) {
					sql.append(" and login like '" + bean.getLogin() + "%'");
				}

			}

			System.out.println("your query :-" + sql.toString());

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();
			System.out.println("resultset executed");

			while (rs.next()) {

				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getInt(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));

				list.add(bean);

			}

		} catch (Exception e) {
			e.getMessage();

		} finally {
			conn.close();
		}
		return list;

	}
}
