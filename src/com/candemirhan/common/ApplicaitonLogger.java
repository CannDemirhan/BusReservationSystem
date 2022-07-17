package com.candemirhan.common;

import java.util.logging.Logger;

import com.candemirhan.util.PropUtility;

public class ApplicaitonLogger {
	
	private static ApplicaitonLogger instance;
	Logger logger;
	
	private ApplicaitonLogger() { super();}
	
	public static ApplicaitonLogger getInstance()
	{
		if(ApplicaitonLogger.instance == null)
			ApplicaitonLogger.instance = new ApplicaitonLogger();
		return ApplicaitonLogger.instance;
	}
	
	private Logger getLogger()
	{
		if(this.logger == null)
		{
			this.logger = Logger.getLogger("Bus Reservation Server Logger");
			this.logger.setLevel(PropUtility.getInstance().getLogLevel());
		}
		return this.logger;
	}

	public void logInfo(String message)
	{
		this.getLogger().info(message);
	}
	public void logWarning(String message)
	{
		this.getLogger().warning(message);
	}
	public void logError(String message)
	{
		this.getLogger().severe(message);
	}
}
