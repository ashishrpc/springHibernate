package com.arpc.psvm.modelPsvm.oneToMany;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="CART")
public class Cart {
	
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
	
	@Column(name="total")
	private double total;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="cart")
	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Set<Items> items;
	/*
	 <set name="items" table="ITEMS" fetch="select">
			<key>
				<column name="cart_id" not-null="true"></column>
			</key>
			<one-to-many class="Items"/>
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Items> getItems() {
		return items;
	}

	public void setItems(Set<Items> items) {
		this.items = items;
	}
	
	
}
