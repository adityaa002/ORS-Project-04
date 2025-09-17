package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.bean.FacultyBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

/**
 * FacultyModel handles database operations for FacultyBean including add, update, delete, findByPk, findByEmail, and search.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
public class FacultyModel {

	private static Logger log = Logger.getLogger(FacultyModel.class);

	/**
	 * Returns next primary key for Faculty table.
	 * 
	 * @return next primary key as Integer
	 * @throws Exception if database error occurs
	 */
	public Integer nextPk() throws Exception {
		log.debug("FacultyModel nextPk method started");
		int pk = 0;
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_faculty");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getInt(1);
		}
		JDBCDataSource.closeConnection(conn);
		log.debug("FacultyModel nextPk method ended");
		return pk + 1;
	}

	/**
	 * Adds a new faculty record.
	 * 
	 * @param bean FacultyBean object
	 * @return primary key of inserted record
	 * @throws Exception if database error occurs or duplicate email exists
	 */
	public long add(FacultyBean bean) throws Exception {
		log.debug("FacultyModel add method started");
		CollegeModel collegeModel = new CollegeModel();
		CollegeBean collegeBean = collegeModel.findByPK(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPK(bean.getCourseId());
		bean.setCourseName(courseBean.getName());

		SubjectModel subjectModel = new SubjectModel();
		SubjectBean subjectBean = subjectModel.findByPk(bean.getSubjectId());
		bean.setSubjectName(subjectBean.getName());

		FacultyBean existBean = findByEmail(bean.getEmail());

		if (existBean != null) {
			throw new DuplicateRecordException("email already exist..!!");
		}

		int pk = nextPk();

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn
				.prepareStatement("insert into st_faculty values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		pstmt.setLong(1, pk);
		pstmt.setString(2, bean.getFirstName());
		pstmt.setString(3, bean.getLastName());
		pstmt.setDate(4, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(5, bean.getGender());
		pstmt.setString(6, bean.getMobileNo());
		pstmt.setString(7, bean.getEmail());
		pstmt.setLong(8, bean.getCollegeId());
		pstmt.setString(9, bean.getCollegeName());
		pstmt.setLong(10, bean.getCourseId());
		pstmt.setString(11, bean.getCourseName());
		pstmt.setLong(12, bean.getSubjectId());
		pstmt.setString(13, bean.getSubjectName());
		pstmt.setString(14, bean.getCreatedBy());
		pstmt.setString(15, bean.getModifiedBy());
		pstmt.setTimestamp(16, bean.getCreatedDatetime());
		pstmt.setTimestamp(17, bean.getModifiedDatetime());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data inserted : " + i);

		log.debug("FacultyModel add method ended");
		return pk;
	}

	/**
	 * Updates an existing faculty record.
	 * 
	 * @param bean FacultyBean object
	 * @throws Exception if database error occurs or duplicate email exists
	 */
	public void update(FacultyBean bean) throws Exception {
		log.debug("FacultyModel update method started");
		CollegeModel collegeModel = new CollegeModel();
		CollegeBean collegeBean = collegeModel.findByPK(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPK(bean.getCourseId());
		bean.setCourseName(courseBean.getName());

		SubjectModel subjectModel = new SubjectModel();
		SubjectBean subjectBean = subjectModel.findByPk(bean.getSubjectId());
		bean.setSubjectName(subjectBean.getName());

		FacultyBean existBean = findByEmail(bean.getEmail());

		if (existBean != null && bean.getId() != existBean.getId()) {
			throw new DuplicateRecordException("email already exist..!!");
		}

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_faculty set first_name = ?, last_name = ?, dob = ?, gender = ?, mobile_no = ?, email = ?, college_id = ?, college_name = ?, course_id = ?, course_name = ?, subject_id = ?, subject_name = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ?  where id = ?");

		pstmt.setString(1, bean.getFirstName());
		pstmt.setString(2, bean.getLastName());
		pstmt.setDate(3, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(4, bean.getGender());
		pstmt.setString(5, bean.getMobileNo());
		pstmt.setString(6, bean.getEmail());
		pstmt.setLong(7, bean.getCollegeId());
		pstmt.setString(8, bean.getCollegeName());
		pstmt.setLong(9, bean.getCourseId());
		pstmt.setString(10, bean.getCourseName());
		pstmt.setLong(11, bean.getSubjectId());
		pstmt.setString(12, bean.getSubjectName());
		pstmt.setString(13, bean.getCreatedBy());
		pstmt.setString(14, bean.getModifiedBy());
		pstmt.setTimestamp(15, bean.getCreatedDatetime());
		pstmt.setTimestamp(16, bean.getModifiedDatetime());
		pstmt.setLong(17, bean.getId());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data updated => " + i);
		log.debug("FacultyModel update method ended");
	}

	/**
	 * Deletes a faculty record.
	 * 
	 * @param bean FacultyBean object
	 * @throws Exception if database error occurs
	 */
	public void delete(FacultyBean bean) throws Exception {
		log.debug("FacultyModel delete method started");
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("delete from st_faculty where id = ?");
		pstmt.setLong(1, bean.getId());
		int i = pstmt.executeUpdate();
		JDBCDataSource.closeConnection(conn);
		System.out.println("data deleted :" + i);
		log.debug("FacultyModel delete method ended");
	}

	/**
	 * Finds a faculty by primary key.
	 * 
	 * @param id primary key
	 * @return FacultyBean object if found, else null
	 * @throws Exception if database error occurs
	 */
	public FacultyBean findByPk(long id) throws Exception {
		log.debug("FacultyModel findByPk method started");
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_faculty where id = ?");
		pstmt.setLong(1, id);
		ResultSet rs = pstmt.executeQuery();
		FacultyBean bean = null;
		while (rs.next()) {
			bean = new FacultyBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCourseId(rs.getLong(10));
			bean.setCourseName(rs.getString(11));
			bean.setSubjectId(rs.getLong(12));
			bean.setSubjectName(rs.getString(13));
			bean.setCreatedBy(rs.getString(14));
			bean.setModifiedBy(rs.getString(15));
			bean.setCreatedDatetime(rs.getTimestamp(16));
			bean.setModifiedDatetime(rs.getTimestamp(17));
		}
		JDBCDataSource.closeConnection(conn);
		log.debug("FacultyModel findByPk method ended");
		return bean;
	}

	/**
	 * Finds a faculty by email.
	 * 
	 * @param email email address
	 * @return FacultyBean object if found, else null
	 * @throws Exception if database error occurs
	 */
	public FacultyBean findByEmail(String email) throws Exception {
		log.debug("FacultyModel findByEmail method started");
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_faculty where email = ?");
		pstmt.setString(1, email);
		ResultSet rs = pstmt.executeQuery();
		FacultyBean bean = null;
		while (rs.next()) {
			bean = new FacultyBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCourseId(rs.getLong(10));
			bean.setCourseName(rs.getString(11));
			bean.setSubjectId(rs.getLong(12));
			bean.setSubjectName(rs.getString(13));
			bean.setCreatedBy(rs.getString(14));
			bean.setModifiedBy(rs.getString(15));
			bean.setCreatedDatetime(rs.getTimestamp(16));
			bean.setModifiedDatetime(rs.getTimestamp(17));
		}
		JDBCDataSource.closeConnection(conn);
		log.debug("FacultyModel findByEmail method ended");
		return bean;
	}

	/**
	 * Searches faculty records based on criteria.
	 * 
	 * @param bean     FacultyBean with search criteria
	 * @param pageNo   page number for pagination
	 * @param pageSize number of records per page
	 * @return List of FacultyBean matching criteria
	 * @throws Exception if database error occurs
	 */
	public List search(FacultyBean bean, int pageNo, int pageSize) throws Exception {
		log.debug("FacultyModel search method started");
		Connection conn = JDBCDataSource.getConnection();
		StringBuffer sql = new StringBuffer("select * from st_faculty where 1=1");

		if (bean != null) {
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getTime() > 0) {
				sql.append(" and dob like '" + new java.sql.Date(bean.getDob().getTime()) + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		System.out.println("sql query " + sql.toString());
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();
		while (rs.next()) {
			bean = new FacultyBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCourseId(rs.getLong(10));
			bean.setCourseName(rs.getString(11));
			bean.setSubjectId(rs.getLong(12));
			bean.setSubjectName(rs.getString(13));
			bean.setCreatedBy(rs.getString(14));
			bean.setModifiedBy(rs.getString(15));
			bean.setCreatedDatetime(rs.getTimestamp(16));
			bean.setModifiedDatetime(rs.getTimestamp(17));
			list.add(bean);
		}
		JDBCDataSource.closeConnection(conn);
		log.debug("FacultyModel search method ended");
		return list;
	}

}
