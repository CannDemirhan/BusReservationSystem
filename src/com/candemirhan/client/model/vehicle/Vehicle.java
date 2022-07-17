package com.candemirhan.client.model.vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.candemirhan.client.model.passanger.Passanger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table
@Entity
public class Vehicle {

	@Id
	@Column(name = "vehicle_id",unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long vehicleId;
	
	private String label;
	private String company;
	
	@Column(name = "licence_plate_code", unique = true)
	private String licencePlateCode;
	private int    capacity;
	
	@OneToMany(mappedBy = "vehicle_id")
	private List<Passanger> passangerList;
	private Map<Integer,Passanger> passangerMap;

	public Vehicle() 
	{
		super();		
		this.passangerList = new ArrayList<>();
		this.passangerMap = new HashMap<>();
		
		for(int i = 1; i <= this.capacity; i++)
		{
			passangerMap.put(i, null);
		}
	}

	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLicencePlateCode() {
		return licencePlateCode;
	}

	public void setLicencePlateCode(String licencePlateCode) {
		this.licencePlateCode = licencePlateCode;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Passanger> getPassangerList() {
		return passangerList;
	}

	public void setPassangerList(List<Passanger> passangerList) {
		this.passangerList = passangerList;
	}

	public Map<Integer, Passanger> getPassangerMap() {
		return passangerMap;
	}

	public void setPassangerMap(Map<Integer, Passanger> passangerMap) {
		this.passangerMap = passangerMap;
	}
	
}