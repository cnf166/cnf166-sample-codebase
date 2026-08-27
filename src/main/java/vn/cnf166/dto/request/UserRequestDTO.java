package vn.cnf166.dto.request;

import java.io.Serializable;

public class UserRequestDTO implements Serializable {
	private String firstName;
	private String lastName;
	private String job;
	private String phone;
	private String address;

	public UserRequestDTO() {
	}

	public UserRequestDTO(String firstName, String lastName, String job, String phone, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.job = job;
		this.phone = phone;
		this.address = address;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
