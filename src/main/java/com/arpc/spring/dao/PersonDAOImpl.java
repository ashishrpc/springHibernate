package com.arpc.spring.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.arpc.spring.model.Person;

/* id is auto incremental field
create table person(
id SERIAL PRIMARY KEY,
name varchar(100),
country varchar(100) 
)
 */
@Repository
public class PersonDAOImpl implements PersonDAO{
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addPerson(Person p) {
		Session session=sessionFactory.openSession();
		session.persist(p);
		session.flush();
		session.close();
		logger.info("Person saved successfully, Person Details="+p);		
	}

	public void updatePerson(Person p) {
		Session session=sessionFactory.openSession();
		session.update(p);
		session.flush();
		session.close();
		logger.info("Person updated successfully, Person Details="+p);
		
	}

	public List<Person> listPersons() {
		Session session=sessionFactory.openSession();
		List<Person> lstPersons=session.createQuery("From Person").list();//////vvvvvvviiiiiiiiii
		session.flush();
		session.close();
		System.out.println("listPersons()...");
		for(Person p : lstPersons){
			logger.info("Person List::"+p);
		}
		return lstPersons;
	}

	public Person getPersonById(int id) {
		Session session=sessionFactory.openSession();
		Person person=(Person)session.load(Person.class, new Integer(id));//////vvvvvvviiiiiiiiii
		person.getName();
		session.close();
		System.out.println("getPersonById()...");
		return person;
	}

	public void removePerson(int id) {
		Session session =sessionFactory.openSession();
		Person person=(Person)session.load(Person.class, new Integer(id)); ////vvvvvvviiiiiiiiii
		if(person!=null){
			session.delete(person);
		}
		session.flush();
		session.close();
		logger.info("Person deleted successfully, person details="+person);
	}
	
}
