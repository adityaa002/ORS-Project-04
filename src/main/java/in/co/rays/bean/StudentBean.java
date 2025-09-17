package in.co.rays.bean;

import java.util.Date;

/**
 * StudentBean represents a student entity in the system.
 * It extends {@link BaseBean} and includes student-related
 * attributes such as first name, last name, date of birth,
 * gender, mobile number, email, and associated college details.
 *
 * This bean is typically used to transfer student-related data
 * between different layers of the application.
 *
 * @author   Aditya
 * @version  1.0
 * @since    2025
 */
public class StudentBean extends BaseBean {

	private String firstName;
	private String lastName;
	private Date dob;
	private String gender;
	private String mobileNo;
	private String email;
	private long collegeId;
	private String collegeName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getKey() {
 		return id + "";
	}

	public String getValue() {
 		return firstName+ " " + lastName;
	}

}
