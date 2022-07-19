package com.candemirhan.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Time;
import java.util.Map;

import com.candemirhan.common.ApplicaitonLogger;
import com.candemirhan.common.CommonData;
import com.candemirhan.server.data.ReservationalOperation;

public class ClientHandler implements Runnable {
	
	private final Socket socket;
	
	private PrintWriter printWriter;
	private BufferedReader buffRead;
	
	private ObjectOutputStream objOutputStream;
	private ObjectInputStream objInputStream;
	
	public ClientHandler(Socket socket)
	{
		this.socket = socket;
		this.printWriter = null;
		this.buffRead = null; 
		this.objInputStream = null;
		this.objOutputStream = null;
	}

	@Override
	public void run() {

		try {
			this.printWriter = new PrintWriter(socket.getOutputStream(),true);
			this.buffRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			this.objOutputStream = new ObjectOutputStream(socket.getOutputStream());
			this.objInputStream = new ObjectInputStream(socket.getInputStream());
			
			String line;
			while((line = buffRead.readLine()) != null)
				this.processRequest(line);
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(printWriter != null)
				{
					printWriter.close();
				}
				if(buffRead != null)
				{
					buffRead.close();
					socket.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void processRequest(String line) throws IOException
	{
		line = line.trim();
		ApplicaitonLogger.getInstance().logInfo("Received from Client " + line);
		
		if(line.startsWith("INTR:"))
		{
			CommonData.getInstance()
						.registerClients(line.substring(5));
			this.printWriter.println("Welcome " + line.substring(5));
		}
		else
		{
			if(line.startsWith("DATE:"))
			{
				if(line.startsWith("DATE:CHECK"))
				{
					CommonData.getInstance().registerClients(line.substring(10));
					ReservationalOperation reserval = new ReservationalOperation();
					Map<String,Time> vehicleap = reserval.checkVehicleFromDB(line.substring(10));
					objOutputStream.writeObject(vehicleap);
				}
				
			}
			else if(line.startsWith("UPDATE:"))
			{
				CommonData.getInstance().registerClients(line.substring(7));
			}
			else if(line.startsWith("DELETE:"))
			{
				CommonData.getInstance().registerClients(line.substring(7));
			}
		}
		this.printWriter.flush();
	}

}
