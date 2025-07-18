package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.CourseBean;
import in.co.rays.util.JDBCDataSource;

public class CourseModel {

	public Integer nextPk() {
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_course");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk + 1;

	}

	public long add(CourseBean bean) throws SQLException {
		Connection conn = null;
		int id = nextPk();

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_course values( ?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, id);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDuration());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();
			conn.commit();

			System.out.println("data inserted : " + i);

		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.close();
		}
		return id;

	}

	public void update(CourseBean bean) throws SQLException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_course set name = ? , duration = ? , description = ? , created_by = ? , modified_by = ? , created_datetime = ? , modified_datetime = ? where id  = ?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDuration());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			System.out.println("data update : " + i);

		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public void delete(int id) throws SQLException {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_course where id = ?");
			pstmt.setLong(1, id);

			int i = pstmt.executeUpdate();
			conn.commit();

			System.out.println("record deleted ...!! " + i);

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public CourseBean findByPk(long l) throws SQLException {

		Connection conn = null;
		CourseBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("Select * from st_course where id = ?");
			pstmt.setLong(1, l);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

			}
			conn.commit();
		}

		catch (Exception e) {
			e.getMessage();
			conn.rollback();
		} finally {
			conn.close();
		}
		return bean;
	}

	public CourseBean findByName(String name) throws SQLException {

		Connection conn = null;
		CourseBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("Select * from st_course where name = ?");
			pstmt.setString(1, name);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

			}
			conn.commit();
		}

		catch (Exception e) {
			e.getMessage();
			conn.rollback();
		} finally {
			conn.close();
		}
		return bean;
	}

	public List search(CourseBean bean, int pageNo, int pageSize) throws SQLException {
		Connection conn = null;
		List list = new ArrayList();

		try {
			conn = JDBCDataSource.getConnection();
			StringBuffer sql = new StringBuffer("select * from st_course where 1=1");

			if (bean != null) {
				if (bean.getName() != null && bean.getName().length() > 0) {
					sql.append(" and name like '" + bean.getName() + "%'");
				}
			}
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				sql.append(" limit " + pageNo + "," + pageSize);
			}
			System.out.println("sql query :- " + sql.toString());

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

				list.add(bean);
			}

		} finally {
			conn.close();
		}
		return list;
	}

	public List<CourseBean> list() throws SQLException {
		return search(null, 0, 0);
	}

}
