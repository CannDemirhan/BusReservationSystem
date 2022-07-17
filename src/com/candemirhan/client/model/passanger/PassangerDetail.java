package com.candemirhan.client.model.passanger;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table
@Entity
public class PassangerDetail {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "passanger_detail_id", unique = true)
	private long passengerDetailId;
	
	@Column(name = "identity_number", unique = true)
	private String identityNumber;
	private Gender gender;
	
	@Column(name = "phone_number" ,unique = true)
	private String phoneNumber;
	
	@Column(unique = true)
	private String email;
	private Date birthdate;
	
	@Lob
	private byte[] picture;

	@OneToOne(mappedBy = "passangerDetail")
	private Passanger passanger;
	
	public PassangerDetail() {super();}

	public long getPassengerDetailId() {
		return passengerDetailId;
	}

	public void setPassengerDetailId(long passengerDetailId) {
		this.passengerDetailId = passengerDetailId;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Passanger getPassanger() {
		return passanger;
	}

	public void setPassanger(Passanger passanger) {
		this.passanger = passanger;
	}
	
	

}
