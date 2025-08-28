package in.co.rays.bean;

/**
 * RoleBean represents a role entity in the system. It extends {@link BaseBean}
 * and defines role-related attributes such as name and description.
 *
 * The class also provides predefined constants for common roles like ADMIN,
 * STUDENT, COLLEGE, KIOSK, and FACULTY.
 *
 * This bean is typically used to transfer role-related data between different
 * layers of the application.
 *
 * @author Aditya
 * @version 1.0
 * @since 2025
 */
public class RoleBean extends BaseBean {

	public static final int ADMIN = 1;
	public static final int STUDENT = 2;
	public static final int COLLEGE = 3;
	public static final int KIOSK = 4;
	public static final int FACULTY = 5;

	private String name;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		return id + "";
	}

	public String getValue() {

		return name;
	}

}
