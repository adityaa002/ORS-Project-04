package in.co.rays.bean;

/**
 * CourseBean represents a course entity in the system.
 * It extends {@link BaseBean} and includes attributes such as
 * name, duration, and description.
 *
 * @author   Aditya
 * @version  1.0
 * @since    2025
 */
public class CourseBean extends BaseBean {

	private String name;
	private String duration;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
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
