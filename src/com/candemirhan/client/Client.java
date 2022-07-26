package com.candemirhan.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

import com.candemirhan.client.communucation.ServerCommunucation;
import com.candemirhan.client.model.vehicle.Vehicle;
import com.candemirhan.client.view.Menu;

public class Client {
	
	private String id;
	private ServerCommunucation serverCommunication;
	private Socket socket;
	private Scanner scanner;
	
	public Client()
	{
		this.id = UUID.randomUUID().toString();
	}

	public static void main(String[] args) {
		
		(new Client()).connectToServer();
		
	}
	
	private void connectToServer()
	{
		try {
			
			this.socket = new Socket(ClientProperties.getInstance().getServerAddress(),ClientProperties.getInstance().getServerPort());
			this.serverCommunication = new ServerCommunucation(this.socket);
			serverCommunication.introduceClient(this.id);

		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	private void startUI() throws IOException 
	{
		this.scanner = new Scanner(System.in);
		Menu mainMenu = new Menu
				.Builder()
				.title("Bus Reservation System")
				.build();
		mainMenu.addMenu(1, "Make   a Reservation");
		mainMenu.addMenu(2, "Update a Reservation");
		mainMenu.addMenu(3, "Cancel a Reservation");
		mainMenu.addMenu(99,"Terminate the Program");
		
		int selection = 0;
		while(selection != 99)
		{
			selection = mainMenu.show().readInteger();
			try {
				
				this.processSelection(selection);
			
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.scanner.close();
	}
	
	private void processSelection(int selection) throws IOException, ClassNotFoundException 
	{
		switch(selection)
		{
			case 1 :{
				System.out.println("Please Enter a Date ");
				System.out.print("Year : ");
				String year = scanner.nextLine();
				System.out.print("Month : ");
				String month = scanner.nextLine();
				System.out.println("Day of Month : ");
				String day = scanner.nextLine();
				@SuppressWarnings("unchecked")
				List<Vehicle> reply = (List<Vehicle>) serverCommunication
						.askForABusByDate(
								LocalDate.of(
										Integer.parseInt(year), 
										Integer.parseInt(month),
										Integer.parseInt(day)
										)
									);
				selectVehicleToRegistration(reply);
			}
			case 2 :{
				System.out.println("Please Enter a PNR Code");
				String codePNR = scanner.nextLine();
				String reply = serverCommunication.checkForReservation(codePNR);
				if(reply.equals("EXISTED"))
					serverCommunication.askForUpdateByPNR(codePNR);
			}
			case 3 :{
				System.out.println("Please Enter a PNR Code");
				String codePNR = scanner.nextLine();
				String reply = serverCommunication.checkForReservation(codePNR);
				if(reply.equals("EXISTED"))
					serverCommunication.askForDelete(codePNR);
			}
		}
	}

	private void selectVehicleToRegistration(List<Vehicle> vehicleList) 
	{
		Menu reservationMenu = new Menu.Builder().title("Vehicles and Departure Hours").build();
		int menuCounter = 1;
		vehicleList.stream()
			.filter(s -> s.getPassangerList().contains(null))
			.forEach(s -> {reservationMenu.addMenu(menuCounter, s.getDepartureTime().toString());});
		reservationMenu.addMenu(99, "Return to the Main Menu");
		int selection = reservationMenu.show().readInteger();
		if(selection == 99)
			return;
		this.serverCommunication.makeReservationAccordingToDepartureTime(reservationMenu.getMenu().get(selection));
	}

}
