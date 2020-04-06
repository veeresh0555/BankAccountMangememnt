package com.account.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long custid;
	
	
	@NotNull(message = "First Name cannot be empty")
	private String firstname;
	
	@NotNull(message = "Last Name cannot be empty")
	private String lastname;
	
	@Email(message = "Please Provide valid Email Id")
	private String email;
	
	@NotNull(message = "Mobile Number Cannot be empty")
	@Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",message="Mobile Number is invalid")
	private String mobile;
	
	
	@OneToMany(mappedBy = "cust",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<AccountNumberEntity> acnumber;
	
	public List<AccountNumberEntity> getAcnumber() {
		return acnumber;
	}

	public void setAcnumber(List<AccountNumberEntity> acnumber) {
		this.acnumber = acnumber;
	}

	

	public long getCustid() {
		return custid;
	}

	public void setCustid(long custid) {
		this.custid = custid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
	
	
}
