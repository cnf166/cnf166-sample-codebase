package vn.cnf166.dto.request;

import jakarta.validation.constraints.*;

import java.io.Serializable;

public class AddressDTO implements Serializable {
	private String apartmentNumber;

	private String floor;

	private String building;

	private String streetNumber;

	@NotBlank(message = "street must be not blank")
	@Size(max = 255, message = "street cannot exceed 255 characters")
	private String street;

	@NotBlank(message = "city must be not blank")
	@Size(max = 100, message = "city cannot exceed 100 characters")
	private String city;

	@NotBlank(message = "country must be not blank")
	@Size(max = 100, message = "country cannot exceed 100 characters")
	private String country;

	@NotNull(message = "addressType must be not null")
	private Integer addressType;

	public AddressDTO() {
	}

	public AddressDTO(String apartmentNumber, String floor, String building, String streetNumber,
							String street, String city, String country, Integer addressType) {
		this.apartmentNumber = apartmentNumber;
		this.floor = floor;
		this.building = building;
		this.streetNumber = streetNumber;
		this.street = street;
		this.city = city;
		this.country = country;
		this.addressType = addressType;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getAddressType() {
		return addressType;
	}

	public void setAddressType(Integer addressType) {
		this.addressType = addressType;
	}
}
