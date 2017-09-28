package com.arpc.psvm.modelPsvm.manyToMany;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ITEMS_MTM")
public class ItemsMtm {
	  
	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	/*
	 <id name="id" type="long">
			<column name="item_id" />
			<generator class="identity" />
		</id>
	 */
	
	@Column(name="item_price")
	private double price;
	
	@Column(name="item_desc")
	private String description;

	/* without any property
	  <set name="carts" table="CART_ITEMS" fetch="select" cascade="all">
			<key column="item_id" />
			<many-to-many class="CartMtm" column="cart_id" />
		</set>
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	 
	
	
}
