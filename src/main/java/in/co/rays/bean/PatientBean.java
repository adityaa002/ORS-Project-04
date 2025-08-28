package in.co.rays.bean;

import java.util.Date;

public class PatientBean extends BaseBean {
	
	
	private String name;
	private String mobile;
	private String disease;
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	private Date dob;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	 

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}


	@Override
	public String getKey() {
		return disease;
	}

	@Override
	public String getValue() {
		return disease;
	}

}
