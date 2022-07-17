package com.candemirhan.client.model.passanger;

public enum Gender {

	MALE	("Male"),
	FEMALE	("Female");
	
	private String gender;

	Gender(String gender) 
	{
		this.gender = gender;
	}
	public String getGender(Gender gender)
	{
		return this.gender;
	}
}
