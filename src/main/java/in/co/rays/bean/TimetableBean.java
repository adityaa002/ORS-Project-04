package in.co.rays.bean;

import java.util.Date;

/**
 * TimetableBean represents the timetable entity in the system.
 * It extends {@link BaseBean} and stores details about exam schedules,
 * including semester, exam date, time, course, subject, and description.
 * 
 * This bean is mainly used to encapsulate and transfer timetable-related
 * information across different layers of the application.
 * 
 * Example usage includes managing and retrieving exam schedules for
 * different courses and subjects.
 * 
 * @author   Aditya
 * @version  1.0
 * @since    2025
 */
public class TimetableBean extends BaseBean {

	private String semester;
	private String description;
	private Date examDate;
	private String examTime;
	private long courseId;
	private String courseName;
	private long subjectId;
	private String subjectName;

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
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

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getKey() {
 		return null;
	}

	public String getValue() {
 		return null;
	}

}
