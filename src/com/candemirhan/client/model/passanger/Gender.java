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
	public static Gender parse(String gender) {
		if(gender.equalsIgnoreCase("Male"))
			return Gender.MALE;
		return Gender.FEMALE;
	}
}
