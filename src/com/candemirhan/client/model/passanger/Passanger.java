package com.candemirhan.client.model.passanger;

import java.util.HashSet;
import java.util.Set;

import com.candemirhan.client.model.booking.Ticket;
import com.candemirhan.client.model.vehicle.Vehicle;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table
@Entity
public class Passanger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "passanger_id")
	private long passengerId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@OneToOne
	@JoinColumn(name = "PASSANGER_DETAIL_ID", referencedColumnName = "passanger_detail_id")
	private PassangerDetail passangerDetail;
	
	@ManyToOne
	@JoinColumn(name = "VEHICLE_PASS_ID",referencedColumnName = "vehicle_id")
	private Vehicle vehicle;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "PASSANGER_ID")
	Set<Ticket>	ticketSet;
	
	

	public Passanger() 
	{
		super();
		this.ticketSet = new HashSet<>();
	}

	public Set<Ticket> getTicketSet() {
		return ticketSet;
	}

	public void setTicketSet(Set<Ticket> ticketSet) {
		this.ticketSet = ticketSet;
	}
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
