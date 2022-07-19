package com.candemirhan.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Time;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.UUID;

import com.candemirhan.client.communucation.ServerCommunucation;
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
		Menu menu = new Menu
				.Builder()
				.title("Bus Reservation System")
				.build();
		menu.addMenu(1, "Make   a Reservation");
		menu.addMenu(2, "Update a Reservation");
		menu.addMenu(3, "Cancel a Reservation");
		menu.addMenu(99,"Terminate the Program");
		
		int selection = 0;
		while(selection != 99)
		{
			selection = menu.show().readInteger();
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
				System.out.println("Please Enter a Date (dd-MM-yyyy)");
				Map<String,Time> reply = serverCommunication.askForABusByDate(scanner.nextLine());
				
				reply
				.entrySet()
				.stream()
				.forEach(s -> System.out.println(s.getKey() + " - " + s.getValue()));
				
				// select vehicle to register ticket
				
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

}
