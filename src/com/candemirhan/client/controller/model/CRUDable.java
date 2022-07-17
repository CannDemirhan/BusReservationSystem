package com.candemirhan.client.controller.model;


import org.hibernate.Session;

import com.candemirhan.util.HibernateUtility;

public interface CRUDable<T> {
	
	public abstract void create(T entity);
	public abstract void retreive();
	public abstract void update(long id, T entity);
	public abstract void delete(long id);
	
	public abstract T find(long id);

	public default Session dbConnectionViaHibernate()
	{
		return HibernateUtility.getSessionFactory().openSession();
	}
}
