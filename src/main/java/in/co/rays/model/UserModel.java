package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.util.EmailBuilder;
import in.co.rays.util.EmailMessage;
import in.co.rays.util.EmailUtility;
import in.co.rays.util.JDBCDataSource;

/**
 * UserModel handles all database operations related to UserBean such as add,
 * update, delete, search, authentication, registration, and password
 * management.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
public class UserModel {

	private static Logger log = Logger.getLogger(UserModel.class);

	/**
	 * Returns next primary key for user table.
	 * 
	 * @return next primary key as Integer
	 */
	public Integer nextPk() {
		log.debug("UserModel nextPk method started");

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_user");
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				pk = rs.getInt(1);
			}

		} catch (Exception e) {
			e.getMessage();
		}
		log.debug("UserModel nextPk method ended");

		return pk + 1;
	}

	/**
	 * Adds a new user to the database.
	 * 
	 * @param bean UserBean containing user details
	 * @return primary key of newly added user
	 * @throws ApplicationException     if database error occurs
	 * @throws DuplicateRecordException if login ID already exists
	 */
	public long add(UserBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("UserModel add method started");

		Connection conn = null;
		int pk = 0;

		UserBean existbean = findByLogin(bean.getLogin());

		if (existbean != null) {
			throw new DuplicateRecordException("Login Id already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into st_user values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
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
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("UserModel add method ended");

		return pk;
	}

	/**
	 * Updates an existing user in the database.
	 * 
	 * @param bean UserBean containing updated user details
	 * @throws ApplicationException     if database error occurs
	 * @throws DuplicateRecordException if login ID conflicts with another user
	 */
	public void update(UserBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("UserModel update method started");

		Connection conn = null;
		UserBean existBean = findByLogin(bean.getLogin());
		if (existBean != null && bean.getId() != existBean.getId()) {
			throw new DuplicateRecordException("LoginId already exist..!");
		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_user set first_name = ? , last_name = ? , login = ? , password = ? , dob = ? , mobile_no = ? , role_id = ? , gender = ? , created_by = ? , modified_by = ? , created_datetime = ? , modified_datetime = ? where id = ?");
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
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setLong(13, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("record update success..!! " + i);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating User ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("UserModel update method ended");

	}

	/**
	 * Deletes a user from the database.
	 * 
	 * @param bean UserBean containing the ID to delete
	 * @throws ApplicationException if database error occurs
	 */
	public void delete(UserBean bean) throws ApplicationException {
		log.debug("UserModel delete method started");

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_user where id = ?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("UserModel delete method ended");

	}

	/**
	 * Finds a user by login ID.
	 * 
	 * @param login login ID of the user
	 * @return UserBean if found, null otherwise
	 * @throws ApplicationException if database error occurs
	 */
	public UserBean findByLogin(String login) throws ApplicationException {
		log.debug("UserModel findByLogin method started");

		Connection conn = null;
		UserBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("Select * from st_user where login = ?;");
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
				bean.setRoleId(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("UserModel findByLogin method ended");

		return bean;

	}

	/**
	 * Finds a user by primary key.
	 * 
	 * @param id primary key of user
	 * @return UserBean if found, null otherwise
	 * @throws ApplicationException if database error occurs
	 */
	public UserBean findByPk(long id) throws ApplicationException {
		log.debug("UserModel findByPk method started");

		Connection conn = null;
		UserBean bean = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

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
				bean.setRoleId(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("UserModel findByPk method ended");

		return bean;
	}

	/**
	 * Authenticates a user using login and password.
	 * 
	 * @param login    user's login ID
	 * @param password user's password
	 * @return UserBean if authentication is successful, null otherwise
	 * @throws ApplicationException if database error occurs
	 */
	public UserBean authenticate(String login, String password) throws ApplicationException {
		log.debug("UserModel authenticate method started");

		UserBean bean = null;
		Connection conn = null;

		StringBuffer sql = new StringBuffer("select * from st_user where login = ? and password = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			pstmt.setString(2, password);
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
				bean.setRoleId(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in get roles");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("UserModel authenticate method ended");

		return bean;
	}

	/**
	 * Searches users based on criteria and pagination.
	 * 
	 * @param bean     UserBean containing search criteria
	 * @param pageNo   page number (1-based)
	 * @param pageSize number of records per page
	 * @return List of UserBean matching criteria
	 * @throws ApplicationException if database error occurs
	 */
	public List<UserBean> search(UserBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("UserModel search method started");

		Connection conn = null;
		ArrayList<UserBean> list = new ArrayList<UserBean>();

		StringBuffer sql = new StringBuffer("select * from st_user where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and last_name like '" + bean.getLastName() + "%'");
			}
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" and login like '" + bean.getLogin() + "%'");
			}
			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" and password like '" + bean.getPassword() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" and dob like " + bean.getDob());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" and mobile_no = " + bean.getMobileNo());
			}
			if (bean.getRoleId() > 0) {
				sql.append(" and role_id = " + bean.getRoleId());
			}
			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" and gender like '" + bean.getGender() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}
		System.out.println(sql.toString());
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
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
				bean.setRoleId(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("UserModel search method ended");

		return list;
	}

	/**
	 * Lists all users.
	 * 
	 * @return List of UserBean
	 * @throws ApplicationException if database error occurs
	 */
	public List<UserBean> list() throws ApplicationException {
		log.debug("UserModel list method called");

		return search(null, 0, 0);
	}

	/**
	 * Registers a new user and sends registration email.
	 * 
	 * @param bean UserBean containing user details
	 * @return primary key of newly registered user
	 * @throws ApplicationException     if database error occurs
	 * @throws DuplicateRecordException if login ID already exists
	 */
	public long registerUser(UserBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("UserModel registerUser method started");

		long pk = add(bean);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		EmailMessage msg = new EmailMessage();
		msg.setTo(bean.getLogin());
		msg.setSubject("Registration is successful for ORS Project");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		log.debug("UserModel registerUser method ended");
		return pk;
	}

	/**
	 * Changes the password of a user and sends a notification email.
	 *
	 * @param id          the user ID
	 * @param oldPassword the current password
	 * @param newPassword the new password to set
	 * @return true if password changed successfully
	 * @throws RecordNotFoundException if user not found or old password mismatch
	 * @throws ApplicationException    if a database or email error occurs
	 */
	public boolean changePassword(Long id, String oldPassword, String newPassword)
			throws RecordNotFoundException, ApplicationException {

		log.debug("UserModel changePassword method started");

		boolean flag = false;
		UserBean beanExist = null;

		beanExist = findByPk(id);
		if (beanExist != null && beanExist.getPassword().equals(oldPassword)) {
			beanExist.setPassword(newPassword);
			try {
				update(beanExist);
			} catch (DuplicateRecordException e) {
				throw new ApplicationException("LoginId is already exist");
			}
			flag = true;
		} else {
			throw new RecordNotFoundException("Login not exist");
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", beanExist.getLogin());
		map.put("password", beanExist.getPassword());
		map.put("firstName", beanExist.getFirstName());
		map.put("lastName", beanExist.getLastName());

		String message = EmailBuilder.getChangePasswordMessage(map);

		EmailMessage msg = new EmailMessage();
		msg.setTo(beanExist.getLogin());
		msg.setSubject("Rays ORS Password has been changed Successfully.");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		log.debug("UserModel changePassword method ended");

		return flag;
	}

	/**
	 * Handles forgotten password by sending the password to the registered email.
	 *
	 * @param login the login ID of the user
	 * @return true if email sent successfully
	 * @throws ApplicationException    if a database or email error occurs
	 * @throws RecordNotFoundException if the login ID does not exist
	 */
	public boolean forgetPassword(String login) throws ApplicationException, RecordNotFoundException {
		log.debug("UserModel forgetPassword method started");

		UserBean userData = findByLogin(login);
		boolean flag = false;

		if (userData == null) {
			throw new RecordNotFoundException("Email ID does not exists !");
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", userData.getLogin());
		map.put("password", userData.getPassword());
		map.put("firstName", userData.getFirstName());
		map.put("lastName", userData.getLastName());
		String message = EmailBuilder.getForgetPasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(login);
		msg.setSubject("Rays ORS Password Reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
		EmailUtility.sendMail(msg);
		flag = true;
		log.debug("UserModel forgetPassword method ended");

		return flag;
	}
}