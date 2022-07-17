package com.candemirhan.common;

import java.util.ArrayList;
import java.util.Map;

public class CommonData {

	private static CommonData instance;
	
	// Data Collections
	private Map<Integer,String> passenger; // For exp
	
	private ArrayList<String> clientList;
	
	private CommonData() 
	{
		super();
		this.clientList = new ArrayList<>();
	}
	
	public static CommonData getInstance()
	{
		if(CommonData.instance == null)
			CommonData.instance = new CommonData();
		return CommonData.instance;
	}
	
	public void registerClients(String clientId)
	{
		this.getClientLists().add(clientId);
	}
	private ArrayList<String> getClientLists()
	{
		if(this.clientList == null)
			this.clientList = new ArrayList<String>();
		return this.clientList;		
	}
	public int getNumOfClients()
	{
		return this.getClientLists().size();
	}
}
