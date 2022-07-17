package com.candemirhan.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.candemirhan.client.model.booking.Ticket;
import com.candemirhan.client.model.passanger.Passanger;
import com.candemirhan.client.model.passanger.PassangerDetail;
import com.candemirhan.client.model.vehicle.Destination;
import com.candemirhan.client.model.vehicle.Vehicle;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = sessionFactory();
	
	private static SessionFactory sessionFactory() {
		
		SessionFactory factory = null;
		
		try {
		
		Configuration config = new Configuration();
		
		// Make configurations
		config.addAnnotatedClass(Passanger.class);
		config.addAnnotatedClass(Vehicle.class);
		config.addAnnotatedClass(PassangerDetail.class);
		config.addAnnotatedClass(Ticket.class);
		config.addAnnotatedClass(Destination.class);
		
		factory = config.configure("hibernate.cfg.xml").buildSessionFactory();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return factory;
	}
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}
