package com.candemirhan.client.controller;

import java.util.List;

import org.hibernate.Session;

import com.candemirhan.client.controller.model.CRUDable;
import com.candemirhan.client.model.passanger.Passanger;

import jakarta.persistence.TypedQuery;

public class PassangerController implements CRUDable<Passanger> {

	@Override
	public void create(Passanger entity) {

		try(Session session = dbConnectionViaHibernate())
		{
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			
			System.out.println("Passanger Added to DB " + entity);
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public void retreive() {
		
		Session session = dbConnectionViaHibernate();
		
		String hql = 
				"SELECT * FROM Passanger as passanger";
		TypedQuery<Passanger> typedQuery = session.createQuery(hql, Passanger.class);
		List<Passanger> passangerList = typedQuery.getResultList();
		
		for (Passanger passanger : passangerList) {
			System.out.println(passanger);
		}
	}

	@Override
	public void update(long id, Passanger newEntity) {
		
		try(Session session = dbConnectionViaHibernate())
		{
			Passanger passangerToBeUpdated = find(id);
			if(passangerToBeUpdated != null)
			{
				passangerToBeUpdated.setFirstName(newEntity.getFirstName());
				passangerToBeUpdated.setLastName(newEntity.getLastName());
				passangerToBeUpdated.setPassangerDetail(newEntity.getPassangerDetail());
				
				session.getTransaction().begin();
				session.merge(passangerToBeUpdated);
				session.getTransaction().commit();
				
				System.out.println("Update is Completed" + id + " " + passangerToBeUpdated);
			}
		}
	}

	@Override
	public void delete(long id) {
		
		try(Session session = dbConnectionViaHibernate())
		{
			Passanger passangerToBeDeleted = find(id);
			if(passangerToBeDeleted != null)
			{
				session.getTransaction().begin();
				session.remove(passangerToBeDeleted);
				session.getTransaction().commit();
				
				System.out.println("Passanger is Deleted " + passangerToBeDeleted);
			}
		}
	}

	@Override
	public Passanger find(long id) 
	{
		Session session = dbConnectionViaHibernate();
		Passanger passanger = null;
		
		try {
			passanger = session.find(Passanger.class,id);
			if(passanger != null)
				System.out.println("Passanger Found" + passanger);
			else
				System.out.println("Passanger not Found");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return passanger;
	}

}
