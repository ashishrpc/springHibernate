package com.arpc.psvm.modelPsvm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="TRANSACTION")
public class Tran {
	@Id
	@Column(name="txn_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	/*
	 <id name="id" type="long">
			<column name="txn_id" />
			<generator class="identity" />
		</id>	
	 */
	@Column(name="txn_date")
	private Date date;
	
	@Column(name="txn_total")
	private double total;
	/*
	 <property name="total" type="double">
			<column name="txn_total" />
		</property>
	 */
	
	@OneToOne(mappedBy="txn")///???????
	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)///???????
	private Customer customer;
	/*
	 <one-to-one name="customer" class="com.journaldev.hibernate.model.Customer"
			cascade="save-update" />
	 */
	@Override
	public String toString(){
		return id+", "+total+", "+customer.getName()+", "+customer.getEmail()+", "+customer.getAddress();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
