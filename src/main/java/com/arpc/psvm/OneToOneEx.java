package com.arpc.psvm;
 

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arpc.psvm.modelPsvm.Customer;
import com.arpc.psvm.modelPsvm.Tran;
/*
-- Create Transaction Table
CREATE TABLE `Transaction` (
  `txn_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `txn_date` date NOT NULL,
  `txn_total` decimal(10,0) NOT NULL,
  PRIMARY KEY (`txn_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- Create Customer table
CREATE TABLE `Customer` (
  `txn_id` int(11) unsigned NOT NULL,
  `cust_name` varchar(20) NOT NULL DEFAULT '',
  `cust_email` varchar(20) DEFAULT NULL,
  `cust_address` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`txn_id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`txn_id`) REFERENCES `Transaction` (`txn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class OneToOneEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tran txn=buildTransaction();
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("appServlet/psvm_servlet-context.xml");
		SessionFactory sessionFactory=(SessionFactory)context.getBean("hibernate4AnnotatedSessionFactory");
		Session session= sessionFactory.openSession();
		Transaction transaction=session.getTransaction();
		transaction.begin();
		session.save(txn.getCustomer()); //session.save(txn);
		transaction.commit();
		session.close();
	}

	public static Tran buildTransaction(){
		Tran tran=new Tran();
		Customer customer=new Customer();
		tran.setDate(new Date());
		tran.setTotal(590);
		customer.setName("Arnav");
		customer.setEmail("Arnav@kmgin.com");
		customer.setAddress("Delhi");
		tran.setCustomer(customer);
		customer.setTxn(tran);
		return tran;
	}
}
