package vn.cnf166.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import vn.cnf166.util.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

	@NotNull
	private String username;

	@NotNull
	private String password;

	@EnumPattern(name = "status", regexp = "ACTIVE|INACTIVE|NONE", message = "status invalid format")
	private UserStatus status;

	@GenderSubset(anyOf = {MALE, FEMALE, OTHER})
	private Gender gender;

	@NotEmpty(message = "addresses can not empty")
	private Set<AddressDTO> addresses;

	// Đối với enum, chỉ bắt được exception khi ta xử lí như này --> còn đâu với enum bth (có/k có subset) thì xử lí
	// theo cách khác
	@NotNull(message = "type must be not null")
	@EnumValue(name = "type", enumClass = UserType.class)
	private String userType;

	@NotNull(message = "dateOfBirth must be not null")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date dateOfBirth;


	public UserRequestDTO() {
	}

	public UserRequestDTO(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
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

	public Set<AddressDTO> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<AddressDTO> addresses) {
		this.addresses = addresses;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
