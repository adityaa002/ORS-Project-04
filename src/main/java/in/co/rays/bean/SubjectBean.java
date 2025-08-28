package in.co.rays.bean;

/**
 * SubjectBean represents the subject entity in the system.
 * It extends {@link BaseBean} and holds details of a subject 
 * such as its name, associated course, course name, and description.
 *
 * This bean is mainly used to encapsulate and transfer 
 * subject-related data across different layers of the application.
 *
 * @author   Aditya
 * @version  1.0
 * @since    2025
 */
public class SubjectBean extends BaseBean {

	private String name;
	private long courseId;
	private String courseName;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
