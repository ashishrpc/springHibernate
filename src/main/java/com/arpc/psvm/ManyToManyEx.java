package com.arpc.psvm;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cascade;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arpc.psvm.modelPsvm.manyToMany.CartMtm;
import com.arpc.psvm.modelPsvm.manyToMany.ItemsMtm;
import com.arpc.psvm.modelPsvm.oneToMany.Cart;
import com.arpc.psvm.modelPsvm.oneToMany.Items;

/*
 CREATE TABLE `Cart` (
  `cart_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cart_total` decimal(10,0) NOT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `Item` (
  `item_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `item_desc` varchar(20) NOT NULL,
  `item_price` decimal(10,0) NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Cart_Items` (
  `cart_id` int(11) unsigned NOT NULL,
  `item_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`cart_id`,`item_id`),
  CONSTRAINT `fk_cart` FOREIGN KEY (`cart_id`) REFERENCES `Cart` (`cart_id`),
  CONSTRAINT `fk_item` FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class ManyToManyEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction transaction=null;
		try{
			ItemsMtm item=new ItemsMtm();
			item.setDescription("Samsong");
			item.setPrice(10000);
			ItemsMtm item2=new ItemsMtm();
			item2.setDescription("Nokia");
			item2.setPrice(12000);
			Set<ItemsMtm> items=new HashSet();
			items.add(item);
			items.add(item2);
			CartMtm cart=new CartMtm();
			cart.setItems(items);
			cart.setTotal(22000);
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("appServlet/psvm_servlet-context.xml");
		SessionFactory sessionFactory=(SessionFactory)context.getBean("hibernate4AnnotatedSessionFactory");
		session= sessionFactory.openSession();
		transaction=session.getTransaction();
		transaction.begin();
		session.save(cart); 
		transaction.commit();
		}catch(Exception ex){
			transaction.rollback();
			ex.printStackTrace();
		}finally{
			
			session.close();
		}
	}

}
