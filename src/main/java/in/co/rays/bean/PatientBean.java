package in.co.rays.bean;

import java.util.Date;

public class PatientBean extends BaseBean {

	private String Name;
	private Date Dob;
	private String Gender;
	private String Mobile;
	private String Disease; 
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Date getDob() {
		return Dob;
	}
	public void setDob(Date dob) {
		Dob = dob;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getDisease() {
		return Disease;
	}
	public void setDisease(String disease) {
		Disease = disease;
	}

	@Override
	public String getKey() {
 		return Disease;
	}
	@Override
	public String getValue() {
 		return Disease;
	}
}
