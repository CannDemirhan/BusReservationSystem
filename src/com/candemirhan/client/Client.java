package com.candemirhan.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

import com.candemirhan.client.communucation.ServerCommunucation;

public class Client {
	
	private String id;
	private ServerCommunucation serverCommunication;
	private Socket socket;
	private BufferedReader buffRead;
	
	public Client()
	{
		this.id = UUID.randomUUID().toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
	private void startUI() {}
	private void processSelection(int selection) {}

}
