package com.candemirhan.client.model.booking;

import java.util.Date;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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
	
	@ManyToOne
	@JoinColumn(name = "PASSANGER_ID", referencedColumnName = "passanger_id")
	private Passanger passanger;
	
	@ManyToOne
	@JoinColumn(name = "VEHICLE_ID", referencedColumnName = "vehicle_id")
	private Vehicle vehicle;
	
	public Ticket() {super();}

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public Date getReservatedOn() {
		return reservatedOn;
	}

	public void setReservatedOn(Date reservatedOn) {
		this.reservatedOn = reservatedOn;
	}

	public Integer getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Passanger getPassanger() {
		return passanger;
	}

	public void setPassanger(Passanger passanger) {
		this.passanger = passanger;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
	
}
