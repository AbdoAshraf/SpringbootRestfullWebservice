package com.rest.app.ws.ui.model.request;

import java.util.List;

public class userDetailsRequestModel {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
     List<AddressRequestModel> Adresses  ;
	public List<AddressRequestModel> getAdresses() {
		return Adresses;
	}
	public void setAdresses(List<AddressRequestModel> adresses) {
		Adresses = adresses;
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
	public void setLastName(String lastname) {
		this.lastName = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
