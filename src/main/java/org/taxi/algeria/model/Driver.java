package org.taxi.algeria.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Driver {

	private String driverID;
	private String firstName;
	private String lastName;
	private String password;
	private Date birthDate;
	private String gender;
	private String email;
	private String license;
	private String phoneNumber;
	private String regID;

	public Driver() {
	}

	public Driver(String driverID, String firstName, String lastName,
			String password, Date birthDate, String gender, String email,
			String license, String phone,String regID) {

		this.driverID = driverID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.birthDate = birthDate;
		this.gender = gender;
		this.email = email;
		this.license = license;
		this.phoneNumber = phone;
		this.regID=regID;

	}

	public String getDriverID() {
		return driverID;
	}

	public void setDriverID(String driverID) {
		this.driverID = driverID;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRegID() {
		return regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}

}
