package com.ty.hospitalapp.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.hospitalapp.dao.AddressDao;
import com.ty.hospitalapp.dto.Address;
import com.ty.hospitalapp.dto.Branch;

public class AddressDaoImp implements AddressDao{
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pun");
	EntityManager entityManager = entityManagerFactory.createEntityManager(); 
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	@Override
	public Address saveAddress(Address address, int bid) {
		Branch branch = entityManager.find(Branch.class, bid);
		if (branch!=null) {
		entityTransaction.begin();
		entityManager.persist(address);
		entityTransaction.commit();
		return address;
		}
		return null;
	}
	@Override
	public boolean deleteAddressById(int aid) {
		Address address = entityManager.find(Address.class, aid);
		if (address!=null) {
			entityTransaction.begin();
			entityManager.remove(address);
			entityTransaction.commit();
			return true;
		}
		return false;
	}
	@Override
	public Address updateAddressById(int aid, Address address) {
		Address addressDb = entityManager.find(Address.class, aid);
		if (addressDb!=null) {
			addressDb.setBranch(address.getBranch());
			addressDb.setCountry(address.getCountry());
			addressDb.setPin(address.getPin());
			addressDb.setState(address.getState());
			addressDb.setStreet(address.getStreet());
			entityTransaction.begin();
			entityManager.merge(addressDb);
			entityTransaction.commit();
			return addressDb;
		}
		return null;
	}
	@Override
	public Address getAddressById(int aid) {
		Address address = entityManager.find(Address.class, aid);
		return address;
	}
	@Override
	public List<Address> getAllAddress() {
		Query query = entityManager.createQuery("select a from Address a");
		List<Address> addresses = query.getResultList();
		return addresses;
	}
	
}
