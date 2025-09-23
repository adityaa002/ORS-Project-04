package in.co.rays.bean;

import java.util.Date;

public class DoctorBean extends BaseBean {

	private String Name;
	private Date Dob;
	private String Mobile;
	private String Expertise;

	
	
	
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

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getExpertise() {
		return Expertise;
	}

	public void setExpertise(String experties) {
		Expertise = experties;
	}


	
	
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return Expertise;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return Expertise;
	}
}
