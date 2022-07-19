package com.candemirhan.client.communucation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Time;
import java.util.Map;

import com.candemirhan.client.controller.TicketContoller;

public class ServerCommunucation {
	
	private PrintWriter 	printWriter;
	private BufferedReader 	buffReader;
	
	private ObjectOutputStream objOutputStream;
	private ObjectInputStream objInputStream;
	
	public ServerCommunucation(Socket socket)
	{
		try {
			this.printWriter = new PrintWriter(socket.getOutputStream(),true);
			this.buffReader  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.objOutputStream = new ObjectOutputStream(socket.getOutputStream());
			this.objInputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}
	
	public void introduceClient(String id) throws IOException
	{
		boolean waitForServer = true;
		
		while(waitForServer)
		{
			waitForServer = this.introduce(id);
			
			if(waitForServer)
			{
				System.out.println("Server is Downloading Data");
				
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private boolean introduce(String id) throws IOException 
	{
		this.printWriter.println("INTR" + id);
		this.printWriter.flush();
		
		String serverResponse = this.buffReader.readLine();
		
		if(serverResponse.startsWith("WAIT"))
			return true;
		else
			return false;
	}

	public Map<String,Time> askForABusByDate(String dateString) throws IOException, ClassNotFoundException 
	{	
		// liste yi al
		String line = "DATE:CHECK:" + dateString;
		printWriter.println(line);
		printWriter.flush();
		return (Map<String, Time>) objInputStream.readObject();
	}

	public String checkForReservation(String codePNR) throws IOException 
	{		
		String line = "PNR:CHECK" + codePNR;
		printWriter.println(line);
		printWriter.flush();
		return buffReader.readLine();
	}

	public void askForUpdateByPNR(String codePNR) 
	{
		String line = "UPDATE:" + codePNR;
		
	}

	public void askForDelete(String codePNR) 
	{
		String line = "DELETE:" + codePNR;
		printWriter.println(line);
		printWriter.flush();
	}

}
