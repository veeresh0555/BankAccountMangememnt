package com.account.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customeraccounts")
public class AccountNumberEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "accnumber")
	private long acno;
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "custid")
	private Customer cust;
	
	@Column(name="balance")
	private Double openingbalance;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public long getAcno() {
		return acno;
	}

	public void setAcno(long acno) {
		this.acno = acno;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	public Double getOpeningbalance() {
		return openingbalance;
	}

	public void setOpeningbalance(Double openingbalance) {
		this.openingbalance = openingbalance;
	}
	
	
	
	
	
}
