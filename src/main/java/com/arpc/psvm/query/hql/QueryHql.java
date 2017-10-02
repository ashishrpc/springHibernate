package com.arpc.psvm.query.hql;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arpc.spring.model.Person;

public class QueryHql {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("appServlet/psvm_servlet-context.xml");
		SessionFactory sessionFactory=(SessionFactory)context.getBean("hibernate4AnnotatedSessionFactory");
		Session session= sessionFactory.openSession();
		Query query=session.createQuery("from  Person where id > ? and name=:personName");
		query.setInteger(0, 1);
		query.setString("personName", "Rakesh");
		List<Person> lst=(List<Person>)query.list();
		session.close();
		context.registerShutdownHook();
		System.out.println(lst);
		for(Person person:lst){
			System.out.println(person.getName());
		}
	}

}
