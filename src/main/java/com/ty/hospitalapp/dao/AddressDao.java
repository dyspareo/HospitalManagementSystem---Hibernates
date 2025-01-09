package com.ty.hospitalapp.dao;

import java.util.List;

import com.ty.hospitalapp.dto.Address;

public interface AddressDao {
	
	Address saveAddress(Address address, int bid);

	boolean deleteAddressById(int aid);
	
	Address updateAddressById(int aid,Address address);
	
	Address getAddressById(int aid);
	
	List<Address> getAllAddress();
	
}
