package com.candemirhan.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.candemirhan.common.ApplicaitonLogger;
import com.candemirhan.common.CommonData;

public class ClientHandler implements Runnable {
	
	private final Socket socket;
	
	private PrintWriter printWriter;
	private BufferedReader buffRead;
	
	public ClientHandler(Socket socket)
	{
		this.socket = socket;
		this.printWriter = null;
		this.buffRead = null; 
	}

	@Override
	public void run() {

		try {
			this.printWriter = new PrintWriter(socket.getOutputStream(),true);
			this.buffRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
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
	
	private void processRequest(String line)
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
			// will be implemented
		}
		this.printWriter.flush();
	}

}
