package com.ty.hospitalapp.dao;

import com.ty.hospitalapp.dto.Hospital;

public interface HospitalDao {

	Hospital saveHospital(Hospital hospital);

	boolean deleteHospitalById(int hid);

	Hospital updateHospitalById(int hid, Hospital hospital);

	Hospital getHospitalById(int hid);

}