package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.bean.PatientBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.util.JDBCDataSource;

public class PatientModel {
	


	private static Logger log = Logger.getLogger(PatientModel.class);

	public Integer nextPK() throws ApplicationException {
		log.debug("PatientModel nextPK method started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_patient");
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
		log.debug("PatientModel nextPK method ended");
		return pk + 1;
	}

	public long add(PatientBean bean) throws ApplicationException {
		log.debug("PatientModel add method started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_patient values(?,?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getMobile());
			pstmt.setString(4, bean.getDisease());
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
			throw new ApplicationException("Exception : Exception in add Patient");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("PatientModel add method ended");
		return pk;
	}

	public void update(PatientBean bean) throws ApplicationException {
		log.debug("PatientModel update method started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_patient set name=?, mobile=?, disease=?, dob=?,gender= ?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getMobile());
			pstmt.setString(3, bean.getDisease());
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
			throw new ApplicationException("Exception in updating Patient");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("PatientModel update method ended");
	}

	public void delete(PatientBean bean) throws ApplicationException {
		log.debug("PatientModel delete method started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_patient where id=?");
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
			throw new ApplicationException("Exception : Exception in delete Patient");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("PatientModel delete method ended");
	}

	public PatientBean findByPK(long pk) throws ApplicationException {
		log.debug("PatientModel findByPK method started");
		Connection conn = null;
		PatientBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_patient where id=?");
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new PatientBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setMobile(rs.getString(3));
				bean.setDisease(rs.getString(4));
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
			throw new ApplicationException("Exception : Exception in findByPK Patient");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("PatientModel findByPK method ended");
		return bean;
	}

	public List<PatientBean> search(PatientBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("PatientModel search method started");
		Connection conn = null;
		List<PatientBean> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer("select * from st_patient where 1=1");

		if (bean != null) {
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getMobile() != null && bean.getMobile().length() > 0) {
				sql.append(" and mobile like '" + bean.getMobile() + "%'");
			}
			if (bean.getDisease() != null && bean.getDisease().length() > 0) {
				sql.append(" and expertise like '" + bean.getDisease() + "%'");
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
				bean = new PatientBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setMobile(rs.getString(3));
				bean.setDisease(rs.getString(4));
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
			throw new ApplicationException("Exception : Exception in search Patient");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("PatientModel search method ended");
		return list;
	}

	public List<PatientBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}


	
	
}
