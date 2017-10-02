package com.arpc.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="PERSON")
//@Proxy(lazy=false)
@NamedQuery(name="person.byId",query="from Person where id = ?")
@NamedNativeQuery(name="person.byId.nativeQuery",query="select * from person where id=?", resultClass=Person.class)
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="employee")
public class Person {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String country;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return this.country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString(){
		return "id="+id+", name="+name+", country="+country;
	}
}
