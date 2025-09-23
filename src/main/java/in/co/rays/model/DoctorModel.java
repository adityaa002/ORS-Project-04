package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.bean.DoctorBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.util.JDBCDataSource;

public class DoctorModel {

	private static Logger log = Logger.getLogger(DoctorModel.class);

	public Integer nextPK() throws ApplicationException {
		log.debug("DoctorModel nextPK method started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_doctor");
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception in nextPK", e);
			throw new ApplicationException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("DoctorModel nextPK method ended");
		return pk + 1;
	}

	public long add(DoctorBean bean) throws ApplicationException {
		log.debug("DoctorModel add method started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_doctor values(?,?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getMobile());
			pstmt.setString(4, bean.getExpertise());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getGender());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (Exception ex) {
				log.error("Rollback Exception in add", ex);
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			log.error("Database Exception in add", e);
			throw new ApplicationException("Exception : Exception in add Doctor");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("DoctorModel add method ended");
		return pk;
	}

	public void update(DoctorBean bean) throws ApplicationException {
		log.debug("DoctorModel update method started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_doctor set name=?, mobile=?, expertise=?, dob=?,gender= ?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getMobile());
			pstmt.setString(3, bean.getExpertise());
			pstmt.setDate(4, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(5, bean.getGender());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.setLong(10, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Doctor");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("DoctorModel update method ended");
	}

	public void delete(DoctorBean bean) throws ApplicationException {
		log.debug("DoctorModel delete method started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_doctor where id=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Doctor");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("DoctorModel delete method ended");
	}

	public DoctorBean findByPK(long pk) throws ApplicationException {
		log.debug("DoctorModel findByPK method started");
		Connection conn = null;
		DoctorBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_doctor where id=?");
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new DoctorBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setMobile(rs.getString(3));
				bean.setExpertise(rs.getString(4));
				bean.setDob(rs.getDate(5));
				bean.setGender(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in findByPK Doctor");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("DoctorModel findByPK method ended");
		return bean;
	}

	public List<DoctorBean> search(DoctorBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("DoctorModel search method started");
		Connection conn = null;
		List<DoctorBean> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer("select * from st_doctor where 1=1");

		if (bean != null) {
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getMobile() != null && bean.getMobile().length() > 0) {
				sql.append(" and mobile like '" + bean.getMobile() + "%'");
			}
			if (bean.getExpertise() != null && bean.getExpertise().length() > 0) {
				sql.append(" and expertise like '" + bean.getExpertise() + "%'");
			}
			if(bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" and gender like '" + bean.getGender() + "%'");
			}
			if (bean.getDob() != null) {
				sql.append(" and dob like'" + new java.sql.Date(bean.getDob().getTime()) + "%'");
			}
			if (bean.getId() > 0) {
				sql.append(" and id=" + bean.getId());
			}
		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new DoctorBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setMobile(rs.getString(3));
				bean.setExpertise(rs.getString(4));
				bean.setDob(rs.getDate(5));
				bean.setGender(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search Doctor");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("DoctorModel search method ended");
		return list;
	}

	public List<DoctorBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}
}
