package in.co.rays.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBCDataSource manages database connections using C3P0 connection pooling.
 * Provides methods to get and close connections safely.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
public final class JDBCDataSource {

	private static JDBCDataSource jds = null;

	private ComboPooledDataSource cpds = null;

	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.bundle.System");

	/**
	 * Private constructor initializes the C3P0 connection pool with properties from
	 * resource bundle.
	 */
	private JDBCDataSource() {
		try {
			cpds = new ComboPooledDataSource();
			cpds.setDriverClass(rb.getString("driver"));
			cpds.setJdbcUrl(rb.getString("url"));
			cpds.setUser(rb.getString("username"));
			cpds.setPassword(rb.getString("password"));
			cpds.setInitialPoolSize(Integer.parseInt(rb.getString("initialpoolsize")));
			cpds.setAcquireIncrement(Integer.parseInt(rb.getString("acquireincrement")));
			cpds.setMaxPoolSize(Integer.parseInt(rb.getString("maxpoolsize")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the singleton instance of JDBCDataSource.
	 * 
	 * @return JDBCDataSource instance
	 */
	public static JDBCDataSource getInstance() {
		if (jds == null) {
			jds = new JDBCDataSource();
		}
		return jds;
	}

	/**
	 * Retrieves a database connection from the pool.
	 * 
	 * @return Connection object, or null if connection fails
	 */
	public static Connection getConnection() {
		try {
			return getInstance().cpds.getConnection();
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Closes the database resources: ResultSet, Statement, and Connection.
	 * 
	 * @param conn Connection to close
	 * @param stmt Statement to close
	 * @param rs   ResultSet to close
	 */
	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes the database resources: Statement and Connection.
	 * 
	 * @param conn Connection to close
	 * @param stmt Statement to close
	 */
	public static void closeConnection(Connection conn, Statement stmt) {
		closeConnection(conn, stmt, null);
	}

	/**
	 * Closes the database Connection.
	 * 
	 * @param conn Connection to close
	 */
	public static void closeConnection(Connection conn) {
		closeConnection(conn, null);
	}
}
