package com.ty.hospitalapp.dao;

import java.util.List;

import com.ty.hospitalapp.dto.Observation;

public interface ObservationDao {
	
	Observation saveObservation(Observation observation, int eid);

	boolean deleteObservationById(int oid);
	
	Observation updateObservationById(int oid,Observation observation);
	
	Observation getObservationById(int oid);
	
	List<Observation> getAllObservation();
	
	List<Observation> getObservationByDoctorName(String name);
	
}
