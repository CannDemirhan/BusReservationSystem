package com.candemirhan.client.model.vehicle;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private Date departureDate;
	
	@Column(name = "departure_time")
	private Time departureTime;
	
	

	@OneToOne
	@JoinColumn(name = "DESTINATION_ID", referencedColumnName = "destination_id")
	private Destination destination;
	
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

	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLicencePlateCode() {
		return licencePlateCode;
	}

	public void setLicencePlateCode(String licencePlateCode) {
		this.licencePlateCode = licencePlateCode;
	}
	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Passanger> getPassangerList() {
		return passangerList;
	}

	public void setPassangerList(List<Passanger> passangerList) {
		this.passangerList = passangerList;
	}
	
}
