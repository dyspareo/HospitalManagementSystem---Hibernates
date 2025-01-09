package com.ty.hospitalapp.dao;

import java.util.List;

import com.ty.hospitalapp.dto.Item;
import com.ty.hospitalapp.dto.MedOrder;

public interface ItemDao {
	
	Item saveItem(Item item, int mid);

	boolean deleteItemById(int iid);
	
	Item updateItemById(int iid,Item item);
	
	Item getItemById(int iid);
	
	List<Item> getAllItem();
}
