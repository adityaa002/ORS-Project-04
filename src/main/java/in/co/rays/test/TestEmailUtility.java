package in.co.rays.test;

import java.util.HashMap;

import in.co.rays.exception.ApplicationException;
import in.co.rays.util.EmailBuilder;
import in.co.rays.util.EmailMessage;
import in.co.rays.util.EmailUtility;

public class TestEmailUtility {

	public static void main(String[] args) {

		try {

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("login", "sharmaneha6489@gmail.com");
			map.put("password", "neha@123");

			String message = EmailBuilder.getUserRegistrationMessage(map);

			EmailMessage emailMessage = new EmailMessage();
			emailMessage.setTo("sharmaneha6489@gmail.com");
			emailMessage.setSubject("Registration is successful for ORS Project");
			emailMessage.setMessage(message);
			emailMessage.setMessageType(EmailMessage.HTML_MSG);

			EmailUtility.sendMail(emailMessage);

			System.out.println("Email sent successfully.");

		} catch (ApplicationException ex) {
			ex.printStackTrace();
			System.err.println("Failed to send email: " + ex.getMessage());
		}

	}

}