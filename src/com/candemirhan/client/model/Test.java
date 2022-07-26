package com.candemirhan.client.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.sql.Date;
import java.sql.Time;

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
		PassangerDetail           passangerDetail           = new PassangerDetail();
		PassangerController       passangerController       = new PassangerController();
		Passanger                 passanger                 = new Passanger();
		TicketContoller           tickCont                  = new TicketContoller();
		Ticket                    ticket                    = new Ticket();
		VehicleController         vehicCont                 = new VehicleController();
		Vehicle                   vehicle                    = new Vehicle();
		DestinationController     desCont                   = new DestinationController();
		Destination               dest                      = new Destination();
		

		Set<Ticket> ticketSet = new HashSet<>();
		
		vehicle.setCapacity(50);
		vehicle.setCompany("Metro");
		vehicle.setLabel("TEMSA");
		vehicle.setLicencePlateCode("34 AB 8888");
		vehicle.setDepartureDate(Date.valueOf(LocalDate.of(2022, Month.JULY, 30)));
		vehicle.setDepartureTime(Time.valueOf(LocalTime.of(15, 30)));
		
		dest.setDepartureDate(Date.valueOf(LocalDate.now()));
		dest.setDestinatedCity("Istanbul");
		dest.setDuration(12);
		dest.setSourceCity("Adana");
		
		passangerDetail.setBirthdate(Date.valueOf(LocalDate.of(1994, 9, 28)));
		passangerDetail.setEmail("c.demirhan01@gmail.com");
		passangerDetail.setGender(Gender.MALE);
		passangerDetail.setIdentityNumber("12345678901");
		passangerDetail.setPhoneNumber("009012345678948");
		passangerDetail.setPicture(null);
		
		ticket.setSeatNumber(1);
		ticketSet.add(ticket);
		
		passanger.setFirstName("Can");
		passanger.setLastName("Demirhan");
		passanger.setPassangerDetail(passangerDetail);
	
		vehicle.getPassangerList().add(passanger);
		
		// Reffing Block
		
		ticket.setVehicle(vehicle);
		ticket.setPassanger(passanger);
		passanger.setPassangerDetail(passangerDetail);
		passanger.setTicketSet(ticketSet);
		passanger.setVehicle(vehicle);
		passangerDetail.setPassanger(passanger);
		dest.setVehicle(vehicle);
		vehicle.setDestination(dest);
		
		// Dao Block

		desCont.create(dest);
		passangerDetailController.create(passangerDetail);
		vehicCont.create(vehicle);
		passangerController.create(passanger);
		tickCont.create(ticket);
		List<Vehicle> vehicleList =
				vehicCont.vehicleListByDate(LocalDate.of(2022, Month.JULY, 30));
		for (Vehicle vehiclee : vehicleList) {
			System.out.println(vehiclee.getTicketList().get(0).getSeatNumber());
		}
	}
}
