package com.candemirhan.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

public class PropUtility {

	private static PropUtility instance;
	
	private Properties clientProperties;
	private String clientPropsFilePath;
	
	private Properties serverProperties;
	private String serverPropertiesFilePath;
	
	private PropUtility() {super();}
	
	public static PropUtility getInstance()
	{
		if(PropUtility.instance == null)
			PropUtility.instance = new PropUtility();
		return PropUtility.instance;
	}
	
	public void setServerPropsFilePath(String filePath, String fileName)
	{
		this.serverPropertiesFilePath = filePath.replaceAll("%20", " ") + "\\" + fileName;
	}
	public void setClientPropsFilePath(String filePath, String fileName)
	{
		this.clientPropsFilePath = filePath.replaceAll("%20", " ") + "\\" + fileName;
	}
	
	private Properties getClientProperties()
	{
		if(this.clientProperties == null)
		{
			this.clientProperties = new Properties();
			try {
				this.clientProperties.load(new FileInputStream(clientPropsFilePath));
			}catch(IOException ex) {
				ex.printStackTrace();
				System.out.println("Client Prop File is not Found");
			}
		}
		return this.clientProperties;
	}
	private Properties getServerProperties()
	{
		if(this.serverProperties == null)
		{
			this.serverProperties = new Properties();
			try {
				this.serverProperties.load(new FileInputStream(serverPropertiesFilePath));
			}catch(IOException ex) {
				ex.printStackTrace();
				System.out.println("Server Prop File is not Found");
			}
		}
		return this.serverProperties;
	}
	public String getClientProperty(String key)
	{
		return (String) this.getClientProperties().get(key);
	}
	public String getServerProperty(String key)
	{
		return (String) this.getServerProperties().get(key);
	}

	public Level getLogLevel() 
	{
		return Level.parse(PropUtility.getInstance().getServerProperties().getProperty("LOG_LEVEL"));
	}
}
