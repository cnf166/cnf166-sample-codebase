package vn.cnf166.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import vn.cnf166.util.PhoneNumber;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserRequestDTO implements Serializable {
	@NotBlank(message = "firstName must be not blank")
	private String firstName;

	@NotNull(message = "lastName must be not null")
	private String lastName;

	@Email(message = "email invalid format")
	private String email;

	//@Pattern(regexp = "^\\d{10}$", message = "phone invalid format")
	@PhoneNumber
	private String phone;

	@NotNull(message = "addresses can not empty")
	private String address;

	@NotNull(message = "dateOfBirth must be not null")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date dateOfBirth;

	@NotEmpty
	List<String> role;

	public UserRequestDTO() {
	}

	public UserRequestDTO(String firstName, String lastName, String email, String phone, String address, Date dateOfBirth, List<String> role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}
}
