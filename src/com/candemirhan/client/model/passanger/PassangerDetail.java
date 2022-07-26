package com.candemirhan.client.model.passanger;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table
@Entity
public class PassangerDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "passanger_detail_id")
	private long passengerDetailId;
	
	@Column(name = "identity_number", unique = true)
	private String identityNumber;
	@Column
	@Enumerated(EnumType.STRING)
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
}
