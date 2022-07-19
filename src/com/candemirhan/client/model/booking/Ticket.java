package com.candemirhan.client.model.booking;

import java.util.Date;
import java.util.Random;

import org.hibernate.annotations.CreationTimestamp;

import com.candemirhan.client.model.passanger.Passanger;
import com.candemirhan.client.model.vehicle.Vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Ticket {
	
	@Id
	@Column(name = "ticket_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ticketId;

	@Column(name = "reservation_date", nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date reservatedOn;
	
	@Column(name = "seat_number", nullable = false, unique = true)
	private int seatNumber;
	
	@Column(name = "pnr_code",nullable = false)
	private long codePNR;
	
	@ManyToOne
	@JoinColumn(name = "PASSANGER_ID", referencedColumnName = "passanger_id")
	private Passanger passanger;
	
	@ManyToOne
	@JoinColumn(name = "VEHICLE_ID", referencedColumnName = "vehicle_id")
	private Vehicle vehicle;
	
	public Ticket() 
	{
		super();
		this.codePNR = randomlyCreatePNR();
	}

	private long randomlyCreatePNR() {
		Random random = new Random();
		return random.nextLong(1_000_000);
	}
}
