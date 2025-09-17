package in.co.rays.util;

/**
 * Represents an email message with recipient, subject, content, and message type.
 * Supports both HTML and plain text messages.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
public class EmailMessage {

	private String to;
	private String subject;
	private String message;
	private int messageType = TEXT_MSG;

	public static final int HTML_MSG = 1;
	public static final int TEXT_MSG = 2;

	/**
	 * Default constructor.
	 */
	public EmailMessage() {
	}

	/**
	 * Parameterized constructor to initialize an email message.
	 *
	 * @param to      recipient email address
	 * @param subject subject of the email
	 * @param message body of the email
	 */
	public EmailMessage(String to, String subject, String message) {
		this.to = to;
		this.subject = subject;
		this.message = message;
	}

	/**
	 * Sets the recipient email address.
	 *
	 * @param to recipient email
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * Returns the recipient email address.
	 *
	 * @return recipient email
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Sets the subject of the email.
	 *
	 * @param subject email subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Returns the subject of the email.
	 *
	 * @return email subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the body/content of the email.
	 *
	 * @param message email body
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Returns the body/content of the email.
	 *
	 * @return email body
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the type of the email message (HTML or TEXT).
	 *
	 * @param messageType message type, either HTML_MSG or TEXT_MSG
	 */
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	/**
	 * Returns the type of the email message.
	 *
	 * @return message type
	 */
	public int getMessageType() {
		return messageType;
	}
}
