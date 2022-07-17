package com.candemirhan.server;

import com.candemirhan.util.PropUtility;

public class ServerProperties {
	
	private static ServerProperties instance;
	private PropUtility propUtil;
	
	private ServerProperties() {super();}
	
	public static ServerProperties getInstance()
	{
		if(ServerProperties.instance == null)
			ServerProperties.instance = new ServerProperties();
		return ServerProperties.instance;
	}
	
	public int getServerPort()
	{
		return Integer.parseInt(PropUtility.getInstance().getServerProperty("SERVER_PORT"));
	}

}
