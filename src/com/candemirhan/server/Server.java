package com.candemirhan.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.candemirhan.common.ApplicaitonLogger;
import com.candemirhan.util.PropUtility;


public class Server {

	private static Server instance;
	
	private Server() {super();}
	
	public Server getInstance()
	{
		if(Server.instance == null)
			Server.instance = new Server();
		return Server.instance;
	}
	
	public static void main(String[] args) {
		
		(new Server()).startServer();
	}
	
	private void startServer()
	{
		try(ServerSocket serSocket = new ServerSocket(ServerProperties.getInstance().getServerPort()))
		{
			serSocket.setReuseAddress(true);
			ApplicaitonLogger.getInstance().logInfo("Server Started and Waiting for Clients...");
			
			while(true)
			{
				Socket socket = serSocket.accept();
				ApplicaitonLogger.getInstance().logInfo("New Client Connected " + socket.getInetAddress().getHostAddress());
				
				ClientHandler clientHandler = new ClientHandler(socket);
				new Thread(clientHandler).start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
