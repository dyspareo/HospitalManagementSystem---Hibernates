package com.ty.hospitalapp.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.hospitalapp.dao.UserDao;
import com.ty.hospitalapp.dto.User;

public class UserDaoImp implements UserDao{

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pun");
	EntityManager entityManager = entityManagerFactory.createEntityManager(); 
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	@Override
	public User saveUser(User user) {
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		return user;
	}

	@Override
	public boolean deleteUserById(int uid) {
		User user = entityManager.find(User.class, uid);
		if (user!=null) {
			entityTransaction.begin();
			entityManager.remove(user);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	@Override
	public User updateUserById(int uid, User user) {
		User userDb = entityManager.find(User.class, uid);
		if(userDb!=null) {
			userDb.setName(user.getName());
			userDb.setEmail(user.getEmail());
			userDb.setPassword(user.getPassword());
			userDb.setPhone(user.getPhone());
			userDb.setRole(user.getRole());
			entityTransaction.begin();
			entityManager.merge(userDb);
			entityTransaction.commit();
		}
		return null;
	}

	@Override
	public User getUserById(int uid) {
		User user = entityManager.find(User.class, uid);
		return user;
	}

	@Override
	public List<User> getAllUser() {
		Query query = entityManager.createQuery("select u from User u");
		List<User> users = query.getResultList();
		return users;
	}

	@Override
	public List<User> getUserByRole(String role) {
		Query query = entityManager.createQuery("select u from User u where u.role = ?1");
		query.setParameter(1, role);
		List<User> users = query.getResultList();
		return users;
	}

}
