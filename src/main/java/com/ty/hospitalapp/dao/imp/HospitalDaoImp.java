package com.ty.hospitalapp.dao.imp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.hospitalapp.dao.HospitalDao;
import com.ty.hospitalapp.dto.Hospital;

public class HospitalDaoImp implements  HospitalDao{

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pun");
	EntityManager entityManager = entityManagerFactory.createEntityManager(); 
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	@Override
	public Hospital saveHospital(Hospital hospital) {
		entityTransaction.begin();
		entityManager.persist(hospital);
		entityTransaction.commit();
		return hospital;
	}

	@Override
	public boolean deleteHospitalById(int hid) {
		Hospital hospital = entityManager.find(Hospital.class, hid);
		if (hospital!=null) {
			entityTransaction.begin();
			entityManager.remove(hospital);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	@Override
	public Hospital updateHospitalById(int hid, Hospital hospital) {
		Hospital hospitalDb = entityManager.find(Hospital.class, hid);
		if(hospitalDb!=null) {
			hospitalDb.setName(hospital.getName());
			hospitalDb.setWebsite(hospital.getWebsite());
			hospitalDb.setBranches(hospital.getBranches());
			entityTransaction.begin();
			entityManager.merge(hospitalDb);
			entityTransaction.commit();
		}
		return null;
	}

	@Override
	public Hospital getHospitalById(int hid) {
		Hospital hospital = entityManager.find(Hospital.class, hid);
		return hospital;
	}

}
