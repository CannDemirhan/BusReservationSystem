package com.candemirhan.client.controller;

import java.util.List;

import org.hibernate.Session;

import com.candemirhan.client.controller.model.CRUDable;
import com.candemirhan.client.model.vehicle.Destination;

import jakarta.persistence.TypedQuery;

public class DestinationController implements CRUDable<Destination> {
	
	@Override
	public void create(Destination entity) {

		try(Session session = dbConnectionViaHibernate())
		{
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			
			System.out.println("Destination Added to DB " + entity);
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public void retreive() {
		
		Session session = dbConnectionViaHibernate();
		
		String hql = 
				"SELECT * FROM Destination as destination";
		TypedQuery<Destination> typedQuery = session.createQuery(hql, Destination.class);
		List<Destination> passangerList = typedQuery.getResultList();
		
		for (Destination destination : passangerList) {
			System.out.println(destination);
		}
	}

	@Override
	public void update(long id, Destination newEntity) {
		
		try(Session session = dbConnectionViaHibernate())
		{
			Destination destinationToBeUpdated = find(id);
			if(destinationToBeUpdated != null)
			{
				destinationToBeUpdated.setSourceCity(newEntity.getSourceCity());
				destinationToBeUpdated.setDestinatedCity(newEntity.getDestinatedCity());
				destinationToBeUpdated.setDuration(newEntity.getDuration());
				destinationToBeUpdated.setDepartureDate(newEntity.getDepartureDate());
//				destinationToBeUpdated.setVehicle(newEntity.getVehicle());
				
				session.getTransaction().begin();
				session.merge(destinationToBeUpdated);
				session.getTransaction().commit();
				
				System.out.println("Update is Completed" + id + " " + destinationToBeUpdated);
			}
		}
	}

	@Override
	public void delete(long id) {
		
		try(Session session = dbConnectionViaHibernate())
		{
			Destination destinationToBeDeleted = find(id);
			if(destinationToBeDeleted != null)
			{
				session.getTransaction().begin();
				session.remove(destinationToBeDeleted);
				session.getTransaction().commit();
				
				System.out.println("Destination is Deleted " + destinationToBeDeleted);
			}
		}
	}

	@Override
	public Destination find(long id) 
	{
		Session session = dbConnectionViaHibernate();
		Destination destination = null;
		
		try {
			destination = session.find(Destination.class,id);
			if(destination != null)
				System.out.println("Destination Found" + destination);
			else
				System.out.println("Destination not Found");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return destination;
	}

}
