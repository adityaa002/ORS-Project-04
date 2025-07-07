package in.co.rays.bean;

import java.util.Date;

public class DataValidator {
	public static boolean isNull(String val) {
		if (val == null || val.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotNull(String val) {
		return !isNull(val);

	}

	public static boolean isInteger(String val) {
		if (isNotNull(val)) {
			try {
				Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;

		}
	}

	public static boolean isEmail(String val) {

		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean isName(String val) {

		String namereg = "^[^-\\s][\\p{L} .'-]+$";

		if (isNotNull(val)) {
			try {
				return val.matches(namereg);
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean isRollNo(String val) {

		String rollreg = "[a-zA-Z]{2}[0-9]{3}";
		if (isNotNull(val)) {
			try {
				return val.matches(rollreg);
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean isPassword(String val) {
		String passreg = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,12}";

		if (isNotNull(val)) {
			try {
				return val.matches(passreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;

		}
	}

	public static boolean isPasswordLength(String val) {

		if (isNotNull(val) && val.length() >= 8 && val.length() <= 12) {
			return true;

		} else {
			return false;
		}

	}

	public static boolean isPhoneNo(String val) {
		String phonereg = "^[6-9][0-9]{9}$";
		if (isNotNull(val)) {

			return val.matches(phonereg);

		} else {
			return false;

		}
	}

	public static boolean isPhoneLength(String val) {

		if (isNotNull(val) && val.length() == 10) {
			return true;
		} else {
			return false;

		}

	}
	
	public static boolean isDate(String val) {
		Date d = null;
		return false;
		
	}

	public static void main(String[] args) {

		// test isNull
		System.out.println("isNull check:-");
		System.out.println(" is it null? " + isNull("aditya"));
		System.out.println(" is it null? " + isNull(""));

		System.out.println();

		System.out.println("isNotNull check:-");
		System.out.println(" is it Notnull? " + isNotNull("aditya"));
		System.out.println(" is it Notnull? " + isNotNull(""));

		System.out.println();

		System.out.println("isInteger check:-");
		System.out.println(" is it Integer? " + isInteger("aditya"));
		System.out.println(" is it Integer? " + isInteger("45"));

		System.out.println();

		System.out.println("isEmail check:-");
		System.out.println(" is it email? " + isEmail("aditya@.com"));
		System.out.println(" is it email? " + isEmail("aditya@gmail.com"));

		System.out.println();

		System.out.println("isName check:-");
		System.out.println(" is it name? " + isName("Adi"));
		System.out.println(" is it name? " + isName("aditya@gmail.com"));

		System.out.println();

		System.out.println("isRoll check:-");
		System.out.println(" is it roll? " + isRollNo("1234"));
		System.out.println(" is it roll? " + isRollNo("AB123"));

		System.out.println();

		System.out.println("isPassword check:-");
		System.out.println(" is it pass? " + isPassword("Passw0rd@123"));
		System.out.println(" is it pass? " + isPassword("pass123"));

		System.out.println();

		System.out.println("isPasswordLength check:-");
		System.out.println(" is it pass length? " + isPasswordLength("Passw0rd@123"));
		System.out.println(" is it pass  length? " + isPasswordLength("pass123"));

		System.out.println();

		System.out.println("isPhoneNo check:-");
		System.out.println(" is it phone? " + isPhoneNo("7854659852"));
		System.out.println(" is it PhoneNo? " + isPhoneNo("5652352155"));
		
		System.out.println();

		System.out.println("isphoneLength check:-");
		System.out.println(" is it phone length? " + isPhoneLength("7854525426"));
		System.out.println(" is it phone  length? " + isPhoneLength("75212548569"));
	}
}
