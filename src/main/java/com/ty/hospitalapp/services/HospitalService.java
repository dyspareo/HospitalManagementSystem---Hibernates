package com.ty.hospitalapp.services;

import com.ty.hospitalapp.dao.imp.HospitalDaoImp;
import com.ty.hospitalapp.dto.Hospital;

public class HospitalService {

	HospitalDaoImp daoImp = new HospitalDaoImp();
	
	public void saveHospitalService(Hospital hospital) {
		Hospital hospitalRt = daoImp.saveHospital(hospital);
		if(hospitalRt != null) {
			System.out.println("Data Saved");
		}
		else {
			System.out.println("Data was not saved !");
		}
	}
	
	public void deleteHospitalService(int hid) {
		boolean flag = daoImp.deleteHospitalById(hid);
		if(flag) {
			System.out.println("Data Deleted");
		}
		else {
			System.out.println("Data was not deleted!");
		}
	}
	
	public Hospital getHospitalById(int hid) {
		Hospital hospital = daoImp.getHospitalById(hid);
		if(hospital!= null) {
			return hospital;
		}
		else {
			return null;
		}
	}
	
	public void updateHospitalService(Hospital hospital,int hid) {
		Hospital hospitalRt = daoImp.updateHospitalById(hid,hospital);
		if(hospitalRt != null) {
			System.out.println("Data Updated");
		}
		else {
			System.out.println("Data was not updated !");
		}
	}
}
