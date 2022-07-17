package com.candemirhan.client;

import com.candemirhan.util.PropUtility;

public class ClientProperties {
	
	private static 	ClientProperties 	instance;
	private 		PropUtility 		propUtil;
	
	private ClientProperties() 
	{ 
		this.propUtil =  PropUtility.getInstance();
		propUtil.setClientPropsFilePath(
				"C:\\Users\\Asus" 		+
				"\\eclipse-workspace" 	+
				"\\OnlineBusReservationSystem\\resources"
				,
				"client.properties");
	}
	
	public static ClientProperties getInstance()
	{
		if(ClientProperties.instance == null)
			ClientProperties.instance = new ClientProperties();
		return ClientProperties.instance;
	}
	
	public String getServerAddress()
	{
		return propUtil.getClientProperty("SERVER_ADDR");
	}
	public int getServerPort()
	{
		return Integer.parseInt(propUtil.getClientProperty("SERVER_PORT"));
	}
	public String getDefaultLanguage()
	{
		return propUtil.getClientProperty("DEFAULT_LANG");
	}
}
