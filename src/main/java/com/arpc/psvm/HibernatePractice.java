package com.arpc.psvm;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arpc.spring.model.Person; 

public class HibernatePractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("appServlet/psvm_servlet-context.xml");
		SessionFactory sessionFactory=(SessionFactory)context.getBean("hibernate4AnnotatedSessionFactory");
		Session session= sessionFactory.openSession();
		Person person =(Person)session.get(Person.class, 1);
		System.out.println(person);
		session.close();
		//update from query
		session=sessionFactory.openSession();
		Query query=session.createQuery("update from Person set country=? where id=:myId");
		query.setString(0, "india");
		query.setLong("myId", 1);
		int i=query.executeUpdate();
		System.out.println("total update: "+i);
		session.close();
		//delete from query
//		session=sessionFactory.openSession();
//		query=session.createQuery("delete from Person where id=:myId");
//		query.setLong("myId", 1);
//		i=query.executeUpdate();
//		System.out.println("total update: "+i);
//		session.close();
		//select data from creteria
		session=sessionFactory.openSession();
		query=session.createQuery("from Person where id=?");
		query.setLong(0, 1);
		List<Person> personList=query.list();
		System.out.println(personList);
		
		context.registerShutdownHook();
		context.close();
	}

}
