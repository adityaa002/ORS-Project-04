package in.co.rays.testClasses;

import java.util.ResourceBundle;

public class PropertyReader {

	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.bundle.System");

	public static String getValue(String key) {
		String val = null;

		try {
			val = rb.getString(key); // {0} is required

		} catch (Exception e) {
			val = key;
		}
		return val;

	}

	public static String getValue(String key, String param) {

		String msg = getValue(key);
		msg = msg.replace("{0}", param);

		return msg;

	}

	public static String getValue(String key, String[] params) {
		String msg = getValue(key);

		for (int i = 0; i < params.length; i++) {
			msg = msg.replace("{" + i + "}" , params[i]);
		}
		return msg;
	}

	public static void main(String[] args) {

		System.out.println("Single param");
		System.out.println(PropertyReader.getValue("error.require"));

		System.out.println();
		System.out.println("custom param");
		System.out.println(PropertyReader.getValue("error.require", "login"));

		System.out.println();
		System.out.println("multiple param replace");
		String[] params = { "login", "password" };
		System.out.println(getValue("error.multipleFields", params));
	}

}
