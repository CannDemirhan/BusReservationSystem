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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
	
	@OneToMany(mappedBy = "passanger", cascade = CascadeType.ALL)
	Set<Ticket>	ticketSet;
	
	public Passanger() 
	{
		super();
		this.ticketSet = new HashSet<>();
	}
}
