package com.arpc.psvm;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cascade;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arpc.psvm.modelPsvm.oneToMany.Cart;
import com.arpc.psvm.modelPsvm.oneToMany.Items;

public class OneToManyEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction transaction=null;
		try{
			Cart cart=new Cart();
			cart.setName("MyCart1");
			Items item1=new Items("I10",10,1,cart);
			Items item2=new Items("I20",20,2,cart);
			Set<Items> itemsSet=new HashSet();
			itemsSet.add(item1);
			itemsSet.add(item2);
			cart.setItems(itemsSet);
			cart.setTotal(10*2 + 20*2);
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("appServlet/psvm_servlet-context.xml");
		SessionFactory sessionFactory=(SessionFactory)context.getBean("hibernate4AnnotatedSessionFactory");
		session= sessionFactory.openSession();
		transaction=session.getTransaction();
		transaction.begin();
		session.save(cart); //@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE) provided in Cart class on @oneToMany then both tables record saved
			//other wise we have to save it sapretely
		//session.save(item1); //items saved due to Cascade
		//session.save(item2); //items saved due to Cascade
		transaction.commit();
		}catch(Exception ex){
			transaction.rollback();
			ex.printStackTrace();
		}finally{
			
			session.close();
		}
	}

}
