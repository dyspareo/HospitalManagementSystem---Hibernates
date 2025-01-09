package com.ty.hospitalapp.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.hospitalapp.dao.MedOrderDao;
import com.ty.hospitalapp.dto.Encounter;
import com.ty.hospitalapp.dto.MedOrder;

public class MedOrderDaoImp implements MedOrderDao{

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pun");
	EntityManager entityManager = entityManagerFactory.createEntityManager(); 
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	
	@Override
	public MedOrder saveMedOrder(MedOrder medOrder, int eid) {
		Encounter encounter = entityManager.find(Encounter.class, eid);
		if (encounter!=null) {
		entityTransaction.begin();
		entityManager.persist(medOrder);
		entityTransaction.commit();
		return medOrder;
		}
		return null;
	}

	@Override
	public MedOrder updateMedOrderById(int mid, MedOrder medOrder) {
		MedOrder medOrderDb = entityManager.find(MedOrder.class, mid);
		if (medOrderDb!=null) {
			medOrderDb.setDname(medOrder.getDname());
			medOrderDb.setEncounter(medOrder.getEncounter());
			medOrderDb.setItems(medOrder.getItems());
			medOrderDb.setOrderDate(medOrder.getOrderDate());
			entityTransaction.begin();
			entityManager.merge(medOrderDb);
			entityTransaction.commit();
			return medOrderDb;
		}
		return null;
	}
	
	@Override
	public boolean deleteMedOrderById(int mid) {
		MedOrder medOrder = entityManager.find(MedOrder.class, mid);
		if (medOrder!=null) {
			entityTransaction.begin();
			entityManager.remove(medOrder);
			entityTransaction.commit();
			return true;
		}
		return false;
	}


	@Override
	public MedOrder getMedOrderById(int mid) {
		MedOrder medOrder = entityManager.find(MedOrder.class, mid);
		return medOrder;
	}

	@Override
	public List<MedOrder> getAllMedOrder() {
		Query query = entityManager.createQuery("select m from MedOrder m");
		List<MedOrder> medOrders = query.getResultList();
		return medOrders;
	}

	@Override
	public List<MedOrder> getMedOrderByDoctorName(String name) {
		Query query = entityManager.createQuery("select m from MedOrder m where m.dname = ?1");
		query.setParameter(1, name);
		List<MedOrder> medOrders = query.getResultList();
		return medOrders;
	}

}
