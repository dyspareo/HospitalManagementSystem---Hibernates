package com.ty.hospitalapp.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.hospitalapp.dao.ObservationDao;
import com.ty.hospitalapp.dto.Encounter;
import com.ty.hospitalapp.dto.MedOrder;
import com.ty.hospitalapp.dto.Observation;

public class ObservationDaoImp implements ObservationDao{

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pun");
	EntityManager entityManager = entityManagerFactory.createEntityManager(); 
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	@Override
	public Observation saveObservation(Observation observation, int eid) {
		Encounter encounter = entityManager.find(Encounter.class, eid);
		if (encounter!=null) {
		entityTransaction.begin();
		entityManager.persist(observation);
		entityTransaction.commit();
		return observation;
		}
		return null;
	}

	@Override
	public boolean deleteObservationById(int oid) {
		Observation observation = entityManager.find(Observation.class, oid);
		if (observation!=null) {
			entityTransaction.begin();
			entityManager.remove(observation);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	@Override
	public Observation updateObservationById(int oid, Observation observation) {
		Observation observationDb = entityManager.find(Observation.class, oid);
		if (observationDb!=null) {
			observationDb.setDname(observation.getDname());
			observationDb.setEncounter(observation.getEncounter());
			observationDb.setRobservation(observation.getRobservation()); 
			entityTransaction.begin();
			entityManager.merge(observationDb);
			entityTransaction.commit();
			return observationDb;
		}
		return null;
	}

	@Override
	public Observation getObservationById(int oid) {
		Observation observation = entityManager.find(Observation.class, oid);
		return observation;
	}

	@Override
	public List<Observation> getAllObservation() {
		Query query = entityManager.createQuery("select o from Observation o");
		List<Observation> observations = query.getResultList();
		return observations;
	}

	@Override
	public List<Observation> getObservationByDoctorName(String name) {
		Query query = entityManager.createQuery("select o from Observation o where o.dname = ?1");
		query.setParameter(1, name);
		List<Observation> observations = query.getResultList();
		return observations;	}

}
