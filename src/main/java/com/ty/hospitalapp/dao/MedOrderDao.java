package com.ty.hospitalapp.dao;

import java.util.List;

import com.ty.hospitalapp.dto.MedOrder;

public interface MedOrderDao {
	
	MedOrder saveMedOrder(MedOrder medOrder, int eid);

	boolean deleteMedOrderById(int mid);
	
	MedOrder updateMedOrderById(int mid,MedOrder medOrder);
	
	MedOrder getMedOrderById(int mid);
	
	List<MedOrder> getAllMedOrder();
	
	List<MedOrder> getMedOrderByDoctorName(String name);

}
