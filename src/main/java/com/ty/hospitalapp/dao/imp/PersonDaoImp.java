package com.ty.hospitalapp.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.hospitalapp.dao.PersonDoa;
import com.ty.hospitalapp.dto.Encounter;
import com.ty.hospitalapp.dto.Person;

public class PersonDaoImp implements PersonDoa{

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pun");
	EntityManager entityManager = entityManagerFactory.createEntityManager(); 
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	@Override
	public Person savePerson(Person person, int eid) {
		Encounter encounter = entityManager.find(Encounter.class, eid);
		if (encounter!=null) {
		entityTransaction.begin();
		entityManager.persist(person);
		entityTransaction.commit();
		return person;
		}
		return null;
	}

	@Override
	public boolean deletePersonById(int pid) {
		Person person = entityManager.find(Person.class, pid);
		if (person!=null) {
			entityTransaction.begin();
			entityManager.remove(person);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	@Override
	public Person updatePersonById(int pid, Person person) {
		Person personDb = entityManager.find(Person.class, pid);
		if (personDb!=null) {
			personDb.setAddress(person.getAddress());
			personDb.setAge(person.getAge());
			personDb.setDob(person.getDob());
			personDb.setEmail(person.getEmail());
			personDb.setEncounters(person.getEncounters());
			personDb.setGender(person.getGender());
			personDb.setName(person.getName());
			personDb.setPhn(person.getPhn());
			entityTransaction.begin();
			entityManager.merge(personDb);
			entityTransaction.commit();
			return personDb;
		}
		return null;
	}

	@Override
	public Person getPersonById(int pid) {
		Person person = entityManager.find(Person.class, pid);
		return person;
	}

	@Override
	public List<Person> getAllPerson() {
		Query query = entityManager.createQuery("select p from Person p");
		List<Person> persons = query.getResultList();
		return persons;
	}

	@Override
	public List<Person> getPersonByGender(String gender) {
		Query query = entityManager.createQuery("select p from Person p where p.gender = ?1");
		query.setParameter(1, gender);
		List<Person> persons = query.getResultList();
		return persons;
	}

	@Override
	public List<Person> getPersonByAge(int age) {
		Query query = entityManager.createQuery("select p from Person p where p.age = ?1");
		query.setParameter(1, age);
		List<Person> persons = query.getResultList();
		return persons;
	}

	@Override
	public List<Person> getPersonByPhone(long phone) {
		Query query = entityManager.createQuery("select p from Person p where p.phone = ?1");
		query.setParameter(1, phone);
		List<Person> persons = query.getResultList();
		return persons;
	}

}
