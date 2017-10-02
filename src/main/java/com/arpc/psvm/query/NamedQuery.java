package com.arpc.psvm.query;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arpc.spring.model.Person;

public class NamedQuery {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("appServlet/psvm_servlet-context.xml");
		SessionFactory sessionFactory=(SessionFactory)context.getBean("hibernate4AnnotatedSessionFactory");
		Session session= sessionFactory.openSession();
		//Query query=session.getNamedQuery("person.byId.nativeQuery");
		//query.setInteger(0, 1); 
		//List<Person> lst=(List<Person>)query.list();
		
//		Criteria criteria=session.createCriteria(Person.class);
//		criteria.add(Restrictions.eq("name", "Ashish"))
//			.add(Restrictions.between("id", 0, 1));
//		List<Person> lst=(List<Person>)criteria.list();
		
//		Person personExample=new Person();
//		Example example=Example.create(personExample).excludeProperty("country");
//		Criteria criteria=session.createCriteria(Person.class).add(example);
//		List<Person> lst=(List<Person>)criteria.list();
/*		
		//cache query
		Query query=session.createQuery("from Person where id>?");
		query.setCacheable(true);
		query.setInteger(0, 0); 
		List<Person> lst=(List<Person>)query.list();
		session.close();
		
		System.out.println(lst);
//		for(Person person:lst){
//			System.out.println(person.getName());
//		}
		session= sessionFactory.openSession();
		Query query1=session.createQuery("from Person where id > ?");
		query1.setCacheable(true);
		query1.setInteger(0, 1); 
		List<Person> lst1=(List<Person>)query1.list();
		session.close();
		
		System.out.println(lst1);
		for(Person person:lst1){
			System.out.println(person.getName());
		}
		*/
		//Don't want to fetch all column, get few columns as below using Object
		/*Query query=session.createQuery("select name, country from Person where id>?");
		query.setInteger(0, 0); 
		List<Object[]> lst=(List<Object[]>)query.list();
		
		//System.out.println(lst);
		for(Object obj[]:lst){
			System.out.println(obj[0]+": "+obj[1]);
		}*/
		//
		SQLQuery query=session.createSQLQuery("select * from Person where id>?");
		query.addEntity(Person.class);
		query.setInteger(0, 0); 
		
		List<Person> lst=(List<Person>)query.list();
		
		//System.out.println(lst);
		for(Person person:lst){
			System.out.println(person.getName()+": "+person.getCountry());
		}
		session.close();
		context.registerShutdownHook();
	}

}
