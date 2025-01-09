package com.ty.hospitalapp.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.hospitalapp.dao.BranchDao;
import com.ty.hospitalapp.dto.Branch;
import com.ty.hospitalapp.dto.Hospital;

public class BranchDaoImp implements BranchDao{

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pun");
	EntityManager entityManager = entityManagerFactory.createEntityManager(); 
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	@Override
	public Branch saveBranch(Branch branch, int hid) {
		Hospital hospital = entityManager.find(Hospital.class, hid);
		if (hospital!=null) {
		entityTransaction.begin();
		entityManager.persist(branch);
		entityTransaction.commit();
		return branch;
		}
		return null;
	}

	@Override
	public boolean deleteBranchById(int bid) {
		Branch branch = entityManager.find(Branch.class, bid);
		if (branch!=null) {
			entityTransaction.begin();
			entityManager.remove(branch);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	@Override
	public Branch updateBranchById(int bid, Branch branch) {
		Branch branchDb = entityManager.find(Branch.class, bid);
		if (branchDb!=null) {
			branchDb.setAddress(branch.getAddress());
			branchDb.setEmail(branch.getEmail());
			branchDb.setEncounters(branch.getEncounters());
			branchDb.setHospital(branch.getHospital());
			branchDb.setName(branch.getName());
			branchDb.setPhone(branch.getPhone());
			entityTransaction.begin();
			entityManager.merge(branchDb);
			entityTransaction.commit();
			return branchDb;
		}
		return null;
	}

	@Override
	public Branch getBranchById(int bid) {
		Branch branch = entityManager.find(Branch.class, bid);
		return branch;
	}

	@Override
	public List<Branch> getAllBranch() {
		Query query = entityManager.createQuery("select b from Branch b");
		List<Branch> branchs = query.getResultList();
		return branchs;
	}

}
