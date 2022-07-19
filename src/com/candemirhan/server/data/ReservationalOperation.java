package com.candemirhan.server.data;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Map;

import com.candemirhan.client.controller.PassangerController;
import com.candemirhan.client.controller.PassangerDetailController;
import com.candemirhan.client.controller.TicketContoller;
import com.candemirhan.client.controller.VehicleController;

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
	
	public Map<String,Time> checkVehicleFromDB(String localDateString) 
	{		
		Map<String,Time> vehicleMap = vehicleController.vehicleMapByDate(LocalDate.parse(localDateString));
		return vehicleMap;
	}

	
	
}
