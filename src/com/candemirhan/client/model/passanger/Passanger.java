package com.candemirhan.client.model.passanger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table
@Entity
public class Passanger {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "passanger_id", unique = true)
	private long passengerId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Lob
	private byte[] picture;
	
	@OneToOne
	@JoinColumn(name = "passanger_detail_id", referencedColumnName = "passanger_id")
	private PassangerDetail passangerDetail;
	
	public Passanger() {super();}

	public long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(long passengerId) {
		this.passengerId = passengerId;
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

	public PassangerDetail getPassangerDetail() {
		return passangerDetail;
	}

	public void setPassangerDetail(PassangerDetail passangerDetail) {
		this.passangerDetail = passangerDetail;
	}
	
	
	
}
