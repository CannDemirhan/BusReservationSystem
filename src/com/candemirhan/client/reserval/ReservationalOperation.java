package com.candemirhan.client.reserval;

import java.time.LocalDate;
import java.util.List;

import com.candemirhan.client.controller.PassangerController;
import com.candemirhan.client.controller.PassangerDetailController;
import com.candemirhan.client.controller.TicketContoller;
import com.candemirhan.client.controller.VehicleController;
import com.candemirhan.client.model.vehicle.Vehicle;

public class ReservationalOperation{
	
	private VehicleController vehicleController;
	private PassangerController passangerController;
	private PassangerDetailController passangerDetailController;
	private TicketContoller ticketContoller;
	
	public ReservationalOperation()
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

	public Vehicle getExactVehicle(String departureTimeString) 
	{	
		return vehicleController.getExactVehicle(departureTimeString);
	}
}
