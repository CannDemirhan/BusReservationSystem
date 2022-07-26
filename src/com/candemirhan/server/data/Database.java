package com.candemirhan.server.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.candemirhan.client.controller.PassangerController;
import com.candemirhan.client.controller.PassangerDetailController;
import com.candemirhan.client.controller.TicketContoller;
import com.candemirhan.client.controller.VehicleController;
import com.candemirhan.client.model.booking.Ticket;
import com.candemirhan.client.model.passanger.Gender;
import com.candemirhan.client.model.passanger.Passanger;
import com.candemirhan.client.model.passanger.PassangerDetail;
import com.candemirhan.client.model.vehicle.Vehicle;
import com.candemirhan.client.reserval.ReservationalOperation;

public class Database {
	
	private BufferedReader 				buffRead;

	private VehicleController         	vehicleController;
	private PassangerController       	passangerController;
	private PassangerDetailController	passangerDetailController;
	private TicketContoller           	ticketContoller;

	private Vehicle         			vehicle;
	private Passanger       			passanger;
	private PassangerDetail 			passangerDetail;
	private Ticket          			ticket;
	
	public Database()
	{
		this.vehicleController = new VehicleController();
		this.passangerController = new PassangerController();
		this.passangerDetailController = new PassangerDetailController();
		this.ticketContoller = new TicketContoller();
	}
	
	public List<Vehicle> checkVehicleFromDB(String localDateString) 
	{
		return vehicleController.vehicleListByDate(LocalDate.parse(localDateString));
	}

	public void createPassanger(String departureTimeString)
	{
		this.buffRead = new BufferedReader(new InputStreamReader(System.in));
		
		ReservationalOperation resval = new ReservationalOperation();
		
		this.vehicle = resval.getExactVehicle(departureTimeString);
		
		try {
			System.out.println("Passanger Informations");
			System.out.println("Please Enter First Passanger Name");
			this.passanger.setFirstName(buffRead.readLine());
			System.out.println("Please Enter Last Name");
			this.passanger.setLastName(buffRead.readLine());
			
			System.out.println("Passanger Detail Information");
			System.out.println("Please Enter ID Number");
			this.passangerDetail.setIdentityNumber(buffRead.readLine());
			System.out.println("Please Enter Email");
			this.passangerDetail.setEmail(buffRead.readLine());
			System.out.println("Please Enter Gender");
			this.passangerDetail.setGender(Gender.parse(buffRead.readLine()));
			System.out.println("Please Enter Birthdate");
			this.passangerDetail.setBirthdate(Date.valueOf(LocalDate.parse(buffRead.readLine())));
			
			this.passanger.setPassangerDetail(passangerDetail);
			this.passanger.setVehicle(vehicle);
			this.passangerDetail.setPassanger(passanger);
			
			this.ticket.setSeatNumber(25);
			this.ticket.setPassanger(passanger);
			this.ticket.setVehicle(vehicle);
			
			this.vehicle.getPassangerList().add(passanger);
			this.vehicle.getTicketList().add(ticket);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				this.buffRead.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
