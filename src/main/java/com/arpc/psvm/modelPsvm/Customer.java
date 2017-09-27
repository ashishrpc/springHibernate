package com.arpc.psvm.modelPsvm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="CUSTOMER")
public class Customer {
/*
 <hibernate-mapping>
	<class name="com.journaldev.hibernate.model.Customer" table="CUSTOMER">	
 */
	@Id
	@Column(name="txn_id",unique=true, nullable=false)
	@GeneratedValue(generator="gen")////??????
	@GenericGenerator(name="gen", strategy="foreign", parameters={@Parameter(name="property",value="txn")}) //??????
	private long id;
	/*
	 <id name="id" type="long">
			<column name="txn_id" />
			<generator class="foreign">
				<param name="property">txn</param>
			</generator>
		</id>
	 */
	@Column(name="cust_name")
	private String name;
	/*
	 <property name="name" type="string">
			<column name="cust_name"></column>
		</property>
	 */
	@Column(name="cust_email")
	private String email;
	
	@Column(name="cust_address")
	private String address;
	
	@OneToOne
	@PrimaryKeyJoinColumn  //??????
	private Tran txn;
	/*
	 <one-to-one name="txn" class="com.journaldev.hibernate.model.Txn"
			constrained="true"></one-to-one>
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Tran getTxn() {
		return txn;
	}

	public void setTxn(Tran txn) {
		this.txn = txn;
	}

	
	
	
}
