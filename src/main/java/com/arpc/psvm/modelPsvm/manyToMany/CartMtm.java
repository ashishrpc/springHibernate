package com.arpc.psvm.modelPsvm.manyToMany;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
 
@Entity
@Table(name="CART_MTM")
public class CartMtm {
	
	@Id
	@Column(name="CART_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	/*
	 <id name="id" type="long">
			<column name="cart_id" />
			<generator class="identity" />
		</id> 
	 */
	
	@Column(name="cart_total")
	private double total;
	
	@ManyToMany(targetEntity=ItemsMtm.class, cascade={CascadeType.ALL})
	@JoinTable(name="CART_ITEMS", joinColumns={@JoinColumn(name="CART_ID")},
									inverseJoinColumns={@JoinColumn(name="ITEM_ID")})
	private Set<ItemsMtm> items=new HashSet();

	/* without any property
	 <set name="items" table="CART_ITEMS" fetch="select" cascade="all">
			<key column="cart_id" />
			<many-to-many class="Item" column="item_id" />
		</set>
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Set<ItemsMtm> getItems() {
		return items;
	}

	public void setItems(Set<ItemsMtm> items) {
		this.items = items;
	}
	
	
}
