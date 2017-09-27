package com.arpc.psvm;

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
		context.registerShutdownHook();
	}

}
