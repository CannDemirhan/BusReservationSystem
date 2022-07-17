package com.candemirhan.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.candemirhan.client.controller.model.CRUDable;
import com.candemirhan.client.model.passanger.PassangerDetail;
import com.candemirhan.client.model.vehicle.Vehicle;

import jakarta.persistence.TypedQuery;

public class VehicleController implements CRUDable<Vehicle>{

	@Override
	public void create(Vehicle entity) {

		try(Session session = dbConnectionViaHibernate()){
			
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			
			System.out.println("Vehicle is Added to DB " + entity);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void retreive() {

		Session session = dbConnectionViaHibernate();
		String hql = 
				"SELECT * FROM Vehicle as vehicle";
		TypedQuery<Vehicle> typedQuery = session.createQuery(hql,Vehicle.class);
		List<Vehicle> vehicleDetailList = typedQuery.getResultList();
		
		for (Vehicle vehicle : vehicleDetailList) {
			System.out.println(vehicle);
		}
	}

	@Override
	public void update(long id, Vehicle entity) {

		try(Session session = dbConnectionViaHibernate())
		{
			Vehicle vehicleToBeUpdated = find(id);
			if(vehicleToBeUpdated != null)
			{
				vehicleToBeUpdated.setLabel(entity.getLabel());
				vehicleToBeUpdated.setCompany(entity.getCompany());
				vehicleToBeUpdated.setLicencePlateCode(entity.getLicencePlateCode());
				vehicleToBeUpdated.setCapacity(entity.getCapacity());
				
				
				session.getTransaction().begin();
				session.merge(vehicleToBeUpdated);
				session.getTransaction().commit();
				
				System.out.println("Update is Complited");
			}
		}
	}

	@Override
	public void delete(long id) {
		
		try(Session session = dbConnectionViaHibernate()){
			
			Vehicle vehicleToBeDeletedById = find(id);
			
			if(vehicleToBeDeletedById != null)
			{
				session.getTransaction().begin();
				session.remove(vehicleToBeDeletedById);
				session.getTransaction().commit();
				
				System.out.println("PassangerDetail Deleted from DB " + id + " " + vehicleToBeDeletedById);
			}
		}catch (Exception e) {
			System.out.println("PassangerDetail not Found via Id :" + id);
		}
	}

	@Override
	public Vehicle find(long id) {

		Session session = dbConnectionViaHibernate();
		Vehicle vehicle = null;
		
		try
		{
			vehicle = session.find(Vehicle.class, id);
			if(vehicle != null)
				System.out.println("Vehicle is Found " + vehicle);
			else
				System.out.println("Vehicle is not Found");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return vehicle;
	}

	

}
