package com.ty.hospitalapp.dao;

import java.util.List;

import com.ty.hospitalapp.dto.Person;

public interface PersonDoa {
	
	Person savePerson(Person person, int eid);

	boolean deletePersonById(int pid);
	
	Person updatePersonById(int pid,Person person);
	
	Person getPersonById(int pid);
	
	List<Person> getAllPerson();
	
	List<Person> getPersonByGender(String gender);
	
	List<Person> getPersonByAge(int age);
	
	List<Person> getPersonByPhone(long phone);
}
