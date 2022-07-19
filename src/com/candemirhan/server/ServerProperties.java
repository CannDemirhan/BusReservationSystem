package com.candemirhan.server;

import com.candemirhan.util.PropUtility;

public class ServerProperties {
	
	private static ServerProperties instance;
	private PropUtility propUtil;
	
	private ServerProperties() 
	{
		this.propUtil =  PropUtility.getInstance();
		propUtil.setServerPropsFilePath(
				"C:\\Users\\Asus" 		+
				"\\eclipse-workspace" 	+
				"\\OnlineBusReservationSystem\\resources"
				,
				"server.properties");
	}
	
	public static ServerProperties getInstance()
	{
		if(ServerProperties.instance == null)
			ServerProperties.instance = new ServerProperties();
		return ServerProperties.instance;
	}
	
	public int getServerPort()
	{
		return Integer.parseInt(this.propUtil.getServerProperty("SERVER_PORT"));
	}

}
