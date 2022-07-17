package com.candemirhan.client.controller;

import java.util.List;

import org.hibernate.Session;

import com.candemirhan.client.controller.model.CRUDable;
import com.candemirhan.client.model.passanger.PassangerDetail;

import jakarta.persistence.TypedQuery;

public class PassangerDetailController implements CRUDable<PassangerDetail> {

	@Override
	public void create(PassangerDetail entity) {

		try(Session session = dbConnectionViaHibernate()){
			
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			
			System.out.println("PassangerDetail is Added to DB " + entity);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void retreive() {

		Session session = dbConnectionViaHibernate();
		String hql = 
				"SELECT * FROM PassangerDetail as passangerDetail";
		TypedQuery<PassangerDetail> typedQuery = session.createQuery(hql,PassangerDetail.class);
		List<PassangerDetail> passangerDetailList = typedQuery.getResultList();
		
		for (PassangerDetail passangerDetail : passangerDetailList) {
			System.out.println(passangerDetail);
		}
	}

	@Override
	public void update(long id, PassangerDetail entity) {

		try(Session session = dbConnectionViaHibernate())
		{
			PassangerDetail passangerDetailToBeUpdated = find(id);
			if(passangerDetailToBeUpdated != null)
			{
				passangerDetailToBeUpdated.setBirthdate(entity.getBirthdate());
				passangerDetailToBeUpdated.setEmail(entity.getEmail());
				passangerDetailToBeUpdated.setGender(entity.getGender());
				passangerDetailToBeUpdated.setIdentityNumber(entity.getIdentityNumber());
				passangerDetailToBeUpdated.setPassengerDetailId(entity.getPassengerDetailId());
				passangerDetailToBeUpdated.setPhoneNumber(entity.getPhoneNumber());
				passangerDetailToBeUpdated.setPicture(entity.getPicture());
				
				session.getTransaction().begin();
				session.merge(passangerDetailToBeUpdated);
				session.getTransaction().commit();
				
				System.out.println("Update is Complited");
			}
		}
	}

	@Override
	public void delete(long id) {
		
		try(Session session = dbConnectionViaHibernate()){
			
			PassangerDetail passangerDetailToBeDeletedById = find(id);
			
			if(passangerDetailToBeDeletedById != null)
			{
				session.getTransaction().begin();
				session.remove(passangerDetailToBeDeletedById);
				session.getTransaction().commit();
				
				System.out.println("PassangerDetail Deleted from DB " + id + " " + passangerDetailToBeDeletedById);
			}
		}catch (Exception e) {
			System.out.println("PassangerDetail not Found via Id :" + id);
		}
	}

	@Override
	public PassangerDetail find(long id) {

		Session session = dbConnectionViaHibernate();
		PassangerDetail passangerDetail = null;
		
		try
		{
			passangerDetail = session.find(PassangerDetail.class, id);
			if(passangerDetail != null)
				System.out.println("PasssangerDetail is Found " + passangerDetail);
			else
				System.out.println("PassangerDetail is not Found");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return passangerDetail;
	}

	

}
