package com.arpc.psvm.secondLevelCache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arpc.spring.model.Person; 

public class HibernateSLC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("appServlet/psvm_servlet-context.xml");
		SessionFactory sessionFactory=(SessionFactory)context.getBean("hibernate4AnnotatedSessionFactory");
		Statistics stats = sessionFactory.getStatistics();
		System.out.println("Stats enabled="+stats.isStatisticsEnabled());
		stats.setStatisticsEnabled(true);
		System.out.println("Stats enabled="+stats.isStatisticsEnabled());
		
		Session session= sessionFactory.openSession();
		Session sessionOther= sessionFactory.openSession();
		Transaction trans=session.getTransaction();
		Transaction transOther=sessionOther.getTransaction();
		printStats(stats, 0);
		Person person =(Person)session.load(Person.class, 1);
		printData(person, stats, 1);
		
		
		person =(Person)session.load(Person.class, 1);
		printData(person, stats, 2);
		
		//clear first level cache, so that second level cache is used
		session.evict(person);
		person =(Person)session.load(Person.class, 1);
		printData(person, stats, 3);
		
		person =(Person)session.load(Person.class, 2);
		printData(person, stats, 4);
		
		person =(Person)sessionOther.load(Person.class, 1);
		printData(person, stats, 5);
		 
		
		sessionFactory.close();
		context.registerShutdownHook();
	}

	private static void printStats(Statistics stats, int i) {
		System.out.println("***** " + i + " *****");
		System.out.println("Fetch Count="
				+ stats.getEntityFetchCount());
		System.out.println("Second Level Hit Count="
				+ stats.getSecondLevelCacheHitCount());
		System.out
				.println("Second Level Miss Count="
						+ stats
								.getSecondLevelCacheMissCount());
		System.out.println("Second Level Put Count="
				+ stats.getSecondLevelCachePutCount());
		System.out.println("***** " + i + " *****");
	}

	private static void printData(Person person, Statistics stats, int count) {
		System.out.println("Person Data: "+count+":: Name="+person.getName()+", Country="+person.getCountry());
		printStats(stats, count);
	}
}
