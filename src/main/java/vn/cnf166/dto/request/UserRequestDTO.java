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

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getJob() {
		return job;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}
}
