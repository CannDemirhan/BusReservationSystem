package com.candemirhan.client.model.passanger;

public class Name {

	private String 	firstName;
	private String 	middleName;
	private String 	lastName;
	
	private boolean middleNamed;
	
	public Name(String firstName, String middleName, String lastName)
	{
		this.firstName = firstName;
		
		if(middleName != null)
		{
			this.middleName = middleName;
			this.middleNamed = true;
		}else
			this.middleNamed = false;
		
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getMiddleName() {
		if(middleNamed)
			return middleName;
		else
			return "";
	}

	public boolean isMiddleNamed() {
		return middleNamed;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getName()
	{
		if(isMiddleNamed())
			return getFirstName() 	+ " " + 
				getMiddleName() 	+ " " + 
				getLastName();
		else
			return getFirstName() + " " + getLastName();
	}
}
