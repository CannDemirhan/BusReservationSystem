package com.candemirhan.client.communucation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerCommunucation {
	
	private PrintWriter 	printWriter;
	private BufferedReader 	buffReader;
	
	public ServerCommunucation(Socket socket)
	{
		try {
			this.printWriter = new PrintWriter(socket.getOutputStream(),true);
			this.buffReader  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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

}
