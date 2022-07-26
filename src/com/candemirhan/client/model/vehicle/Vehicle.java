package com.candemirhan.client.model.vehicle;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.candemirhan.client.model.booking.Ticket;
import com.candemirhan.client.model.passanger.Passanger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table
@Entity
public class Vehicle {

	@Id
	@Column(name = "vehicle_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long vehicleId;
	
	private String label;
	private String company;
	
	@Column(name = "licence_plate_code", unique = true)
	private String licencePlateCode;
	private int    capacity;
	
	@Column(name = "departure_date")
	@Temporal(value = TemporalType.DATE)
	private Date departureDate;
	
	@Column(name = "departure_time")
	@Temporal(value = TemporalType.TIME)
	private Time departureTime;
	
	
	@OneToOne
	@JoinColumn(name = "DESTINATION_ID", referencedColumnName = "destination_id")
	private Destination destination;
	
	@OneToMany(mappedBy = "vehicle")
	private List<Ticket> ticketList;
	
	@OneToMany(mappedBy = "vehicle")
	private List<Passanger> passangerList;
	
	
	public Vehicle() 
	{
		super();		
		this.passangerList = new ArrayList<>();
		
		for(int i = 1; i <= this.capacity; i++)
		{
			passangerList.add(i, null);
		}
	}
}
