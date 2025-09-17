package in.co.rays.util;

import java.util.ResourceBundle;

/**
 * PropertyReader provides utility methods to read and format values
 * from the system property resource bundle.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
public class PropertyReader {

	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.bundle.System");

	/**
	 * Retrieves the value for a given key from the resource bundle.
	 * 
	 * @param key Key to look up
	 * @return Corresponding value or the key itself if not found
	 */
	public static String getValue(String key) {
		String val = null;
		try {
			val = rb.getString(key); // {0} is required
		} catch (Exception e) {
			val = key;
		}
		return val;
	}

	/**
	 * Retrieves the value for a given key and replaces a single placeholder {0} with the given parameter.
	 * 
	 * @param key   Key to look up
	 * @param param Parameter to replace {0} in the value
	 * @return Formatted value
	 */
	public static String getValue(String key, String param) {
		String msg = getValue(key); // {0} is required
		msg = msg.replace("{0}", param);
		return msg;
	}

	/**
	 * Retrieves the value for a given key and replaces multiple placeholders {0}, {1}, ... with the provided parameters.
	 * 
	 * @param key    Key to look up
	 * @param params Array of parameters to replace placeholders
	 * @return Formatted value
	 */
	public static String getValue(String key, String[] params) {
		String msg = getValue(key);
		for (int i = 0; i < params.length; i++) {
			msg = msg.replace("{" + i + "}", params[i]);
		}
		return msg;
	}

	/**
	 * Main method for testing PropertyReader methods.
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {

		System.out.println("Single key example:");
		System.out.println(PropertyReader.getValue("error.require"));

		System.out.println("\nSingle parameter replacement example:");
		System.out.println(PropertyReader.getValue("error.require", "loginId"));

		System.out.println("\nMultiple parameter replacement example:");
		String[] params = { "Roll No", "Student Name" };
		System.out.println(PropertyReader.getValue("error.multipleFields", params));
	}
}
