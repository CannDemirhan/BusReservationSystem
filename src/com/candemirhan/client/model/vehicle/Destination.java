package com.candemirhan.client.model.vehicle;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table
@Entity
public class Destination {
	
	@Id
	@Column(name = "destination_id",nullable = false,unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long destinationId;
	
	@Column(name = "source_city",nullable = false)
	private String sourceCity;
	
	@Column(name = "destinated_city",nullable = false)
	private String destinatedCity;
	
	@Column(name = "duration", nullable = false)
	private int duration;

	@Column (name= "departure_date", nullable = false)
	private Date departureDate;
	
	@OneToOne(mappedBy = "destination")
	private Vehicle vehicle;
	
	public Destination() {super();}

	public long getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(long destinationId) {
		this.destinationId = destinationId;
	}

	public String getSourceCity() {
		return sourceCity;
	}

	public void setSourceCity(String sourceCity) {
		this.sourceCity = sourceCity;
	}

	public String getDestinatedCity() {
		return destinatedCity;
	}

	public void setDestinatedCity(String destinatedCity) {
		this.destinatedCity = destinatedCity;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
}
