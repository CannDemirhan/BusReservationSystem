package com.candemirhan.client.model;

import java.time.LocalDate;
import java.sql.Date;

import com.candemirhan.client.controller.DestinationController;
import com.candemirhan.client.controller.PassangerController;
import com.candemirhan.client.controller.PassangerDetailController;
import com.candemirhan.client.controller.TicketContoller;
import com.candemirhan.client.controller.VehicleController;
import com.candemirhan.client.model.booking.Ticket;
import com.candemirhan.client.model.passanger.Gender;
import com.candemirhan.client.model.passanger.Passanger;
import com.candemirhan.client.model.passanger.PassangerDetail;
import com.candemirhan.client.model.vehicle.Destination;
import com.candemirhan.client.model.vehicle.Vehicle;

public class Test {

	
	public static void main(String[] args) {
		
		PassangerDetailController passangerDetailController = new PassangerDetailController();
		PassangerDetail passangerDetail = new PassangerDetail();
		passangerDetail.setBirthdate(Date.valueOf(LocalDate.of(1994, 9, 27)));
		passangerDetail.setEmail("c.demirhan01@gmail.com");
		passangerDetail.setGender(Gender.MALE);
		passangerDetail.setIdentityNumber("12345678901");
		passangerDetail.setPhoneNumber("009012345678948");
		passangerDetail.setPicture(null);
		passangerDetailController.create(passangerDetail);
		
		TicketContoller tickCont = new TicketContoller();
		Ticket ticket = new Ticket();
		ticket.setSeatNumber(1);
		tickCont.create(ticket);
		
		PassangerController passangerController = new PassangerController();
		Passanger passanger = new Passanger();
		passanger.setFirstName("Can");
		passanger.setLastName("Demirhan");
		passanger.setPassangerDetail(passangerDetail);
		passangerController.create(passanger);

		
		VehicleController vehicCont = new VehicleController();
		Vehicle vehic = new Vehicle();
		vehic.setCapacity(50);
		vehic.setCompany("Metro");
		vehic.setLabel("TEMSA");
		vehic.setLicencePlateCode("34 AB 8888");
		vehic.getPassangerList().add(passanger);
		vehicCont.create(vehic);
		
		DestinationController desCont = new DestinationController();
		Destination dest = new Destination();
		dest.setDepartureDate(Date.valueOf(LocalDate.now()));
		dest.setDestinatedCity("Istanbul");
		dest.setDuration(12);
		dest.setSourceCity("Adana");
		desCont.create(dest);
		
		
	}
}
