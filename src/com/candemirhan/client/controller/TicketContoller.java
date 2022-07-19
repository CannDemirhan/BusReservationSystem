package com.candemirhan.client.controller;

import java.util.List;

import org.hibernate.Session;

import com.candemirhan.client.controller.model.CRUDable;
import com.candemirhan.client.model.booking.Ticket;

import jakarta.persistence.TypedQuery;

public class TicketContoller implements CRUDable<Ticket>{
	
	@Override
	public void create(Ticket entity) {

		try(Session session = dbConnectionViaHibernate())
		{
			session.getTransaction().begin();
			session.merge(entity);
			session.getTransaction().commit();
			
			System.out.println("Ticket Added to DB " + entity);
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public void retreive() {
		
		Session session = dbConnectionViaHibernate();
		
		String hql = 
				"SELECT * FROM Ticket as ticcket";
		TypedQuery<Ticket> typedQuery = session.createQuery(hql, Ticket.class);
		List<Ticket> ticketList = typedQuery.getResultList();
		
		for (Ticket ticket : ticketList) {
			System.out.println(ticket);
		}
	}

	@Override
	public void update(long id, Ticket newEntity) {
		
		try(Session session = dbConnectionViaHibernate())
		{
			Ticket ticketToBeUpdated = find(id);
			if(ticketToBeUpdated != null)
			{
				
				
				session.getTransaction().begin();
				session.merge(ticketToBeUpdated);
				session.getTransaction().commit();
				
				System.out.println("Update is Completed" + id + " " + ticketToBeUpdated);
			}
		}
	}

	@Override
	public void delete(long id) {
		
		try(Session session = dbConnectionViaHibernate())
		{
			Ticket ticketToBeDeleted = find(id);
			if(ticketToBeDeleted != null)
			{
				session.getTransaction().begin();
				session.remove(ticketToBeDeleted);
				session.getTransaction().commit();
				
				System.out.println("Ticket is Deleted " + ticketToBeDeleted);
			}
		}
	}

	@Override
	public Ticket find(long id) 
	{
		Session session = dbConnectionViaHibernate();
		Ticket ticket = null;
		
		try {
			ticket = session.find(Ticket.class,id);
			if(ticket != null)
				System.out.println("Ticket Found" + ticket);
			else
				System.out.println("Ticket not Found");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ticket;
	}

}
