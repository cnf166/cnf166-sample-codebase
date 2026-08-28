package vn.cnf166.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import vn.cnf166.util.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static vn.cnf166.util.Gender.*;

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

	@EnumPattern(name = "status", regexp = "ACTIVE|INACTIVE|NONE", message = "status invalid format")
	private UserStatus status;

	@GenderSubset(anyOf = {MALE, FEMALE, OTHER})
	private Gender gender;

	@NotNull(message = "addresses can not empty")
	private String address;

	// Đối với enum, chỉ bắt được exception khi ta xử lí như này --> còn đâu với enum bth (có/k có subset) thì xử lí
	// theo cách khác
	@NotNull(message = "type must be not null")
	@EnumValue(name = "type", enumClass = UserType.class)
	private String userType;

	@NotNull(message = "dateOfBirth must be not null")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date dateOfBirth;

	@NotEmpty
	List<String> role;

	public UserRequestDTO() {
	}

	public UserRequestDTO(String firstName, String lastName, String email, String phone, UserStatus status, Gender gender, String userType, String address, Date dateOfBirth, List<String> role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.gender = gender;
		this.userType = userType;
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

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
