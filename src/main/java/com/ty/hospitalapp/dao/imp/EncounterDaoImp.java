package com.ty.hospitalapp.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.hospitalapp.dao.EncounterDao;
import com.ty.hospitalapp.dto.Address;
import com.ty.hospitalapp.dto.Branch;
import com.ty.hospitalapp.dto.Encounter;

public class EncounterDaoImp implements EncounterDao {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pun");
	EntityManager entityManager = entityManagerFactory.createEntityManager(); 
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	
	@Override
	public Encounter saveEncounter(Encounter encounter, int bid) {
		Branch branch = entityManager.find(Branch.class, bid);
		if (branch!=null) {
		entityTransaction.begin();
		entityManager.persist(encounter);
		entityTransaction.commit();
		return encounter;
		}
		return null;
	}

	@Override
	public boolean deleteEncounterById(int eid) {
		Encounter encounter = entityManager.find(Encounter.class, eid);
		if (encounter!=null) {
			entityTransaction.begin();
			entityManager.remove(encounter);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	@Override
	public Encounter updateEncounterById(int eid, Encounter encounter) {
		Encounter encounterDb = entityManager.find(Encounter.class, eid);
		if (encounterDb!=null) {
			encounterDb.setBranch(encounter.getBranch());
			encounterDb.setDateOfJoin(encounter.getDateOfJoin());
			encounterDb.setDischarge(encounter.getDischarge());
			encounterDb.setMedOrder(encounter.getMedOrder());
			encounterDb.setObservations(encounter.getObservations());
			encounterDb.setPerson(encounter.getPerson());
			encounterDb.setReason(encounter.getReason());
			entityTransaction.begin();
			entityManager.merge(encounterDb);
			entityTransaction.commit();
			return encounterDb;
		}
		return null;
	}

	@Override
	public Encounter getEncounterById(int eid) {
		Encounter encounter = entityManager.find(Encounter.class, eid);
		return encounter;
	}

	@Override
	public List<Encounter> getAllEncounter() {
		Query query = entityManager.createQuery("select e from Encounter e");
		List<Encounter> encounters = query.getResultList();
		return encounters;
	}

}
