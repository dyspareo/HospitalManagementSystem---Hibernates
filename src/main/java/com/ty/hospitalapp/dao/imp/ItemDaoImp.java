package com.ty.hospitalapp.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.hospitalapp.dao.ItemDao;
import com.ty.hospitalapp.dto.Item;
import com.ty.hospitalapp.dto.MedOrder;

public class ItemDaoImp implements ItemDao{

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pun");
	EntityManager entityManager = entityManagerFactory.createEntityManager(); 
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	@Override
	public Item saveItem(Item item, int mid) {
		MedOrder medOrder = entityManager.find(MedOrder.class, mid);
		if (medOrder!=null) {
		entityTransaction.begin();
		entityManager.persist(item);
		entityTransaction.commit();
		return item;
		}
		return null;
	}

	@Override
	public boolean deleteItemById(int iid) {
		Item item = entityManager.find(Item.class, iid);
		if (item!=null) {
			entityTransaction.begin();
			entityManager.remove(item);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	@Override
	public Item updateItemById(int iid, Item item) {
		Item itemDb = entityManager.find(Item.class, iid);
		if (itemDb!=null) {
			itemDb.setCost(item.getCost());
			itemDb.setItemName(item.getItemName());
			itemDb.setMedOrder(item.getMedOrder());
			itemDb.setQuantity(item.getQuantity());
			entityTransaction.begin();
			entityManager.merge(itemDb);
			entityTransaction.commit();
			return itemDb;
		}
		return null;
	}

	@Override
	public Item getItemById(int iid) {
		Item item = entityManager.find(Item.class, iid);
		return item;
	}

	@Override
	public List<Item> getAllItem() {
		Query query = entityManager.createQuery("select i from Item i");
		List<Item> items = query.getResultList();
		return items;
	}

}
