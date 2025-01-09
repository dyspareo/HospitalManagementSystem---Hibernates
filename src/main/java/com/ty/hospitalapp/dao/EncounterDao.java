package com.ty.hospitalapp.dao;

import java.util.List;

import com.ty.hospitalapp.dto.Encounter;

public interface EncounterDao {

	Encounter saveEncounter(Encounter encounter, int bid);

	boolean deleteEncounterById(int eid);
	
	Encounter updateEncounterById(int eid,Encounter encounter);
	
	Encounter getEncounterById(int eid);
	
	List<Encounter> getAllEncounter();
	
}