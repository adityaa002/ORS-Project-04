package in.co.rays.util;

import java.util.HashMap;

/**
 * Utility class to build HTML email messages for different scenarios like user
 * registration, password recovery, and password change.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
public class EmailBuilder {

	/**
	 * Builds the HTML message for user registration confirmation.
	 *
	 * @param map HashMap containing user login and password details
	 * @return HTML string message for registration
	 */
	public static String getUserRegistrationMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();
		msg.append("<HTML><BODY>");
		msg.append("<H1>Welcome to ORS, ").append(map.get("login")).append("!</H1>");
		msg.append("<P>Your registration is successful. You can now log in and manage your account.</P>");
		msg.append("<P><B>Login Id: ").append(map.get("login")).append("<BR>Password: ").append(map.get("password"))
				.append("</B></P>");
		msg.append("<P>Change your password after logging in for security reasons.</P>");
		msg.append("<P>For support, contact +91 7828798680 or hrd@sunrays.co.in.</P>");
		msg.append("</BODY></HTML>");
		return msg.toString();
	}

	/**
	 * Builds the HTML message for password recovery.
	 *
	 * @param map HashMap containing user's first name, last name, login, and
	 *            password
	 * @return HTML string message for password recovery
	 */
	public static String getForgetPasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();
		msg.append("<HTML><BODY>");
		msg.append("<H1>Password Recovery</H1>");
		msg.append("<P>Hello, ").append(map.get("firstName")).append(" ").append(map.get("lastName")).append(".</P>");
		msg.append("<P>Your login details are:</P>");
		msg.append("<P><B>Login Id: ").append(map.get("login")).append("<BR>Password: ").append(map.get("password"))
				.append("</B></P>");
		msg.append("</BODY></HTML>");
		return msg.toString();
	}

	/**
	 * Builds the HTML message for notifying the user of a successful password
	 * change.
	 *
	 * @param map HashMap containing user's first name, last name, login, and new
	 *            password
	 * @return HTML string message for password change notification
	 */
	public static String getChangePasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();
		msg.append("<HTML><BODY>");
		msg.append("<H1>Password Changed Successfully</H1>");
		msg.append("<P>Dear ").append(map.get("firstName")).append(" ").append(map.get("lastName"))
				.append(", your password has been updated.</P>");
		msg.append("<P>Your updated login details are:</P>");
		msg.append("<P><B>Login Id: ").append(map.get("login")).append("<BR>New Password: ").append(map.get("password"))
				.append("</B></P>");
		msg.append("</BODY></HTML>");
		return msg.toString();
	}
}
