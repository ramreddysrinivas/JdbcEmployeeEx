package com.jdbc.domain;

import java.util.List; 


public class Customer {
	private int cId;
	private String cName;
	private String cEmailId;
	
	private List<Address> addressList;
	
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String name) {
		cName = name;
	}
	public String getcEmailId() {
		return cEmailId;
	}
	public void setcEmailId(String cEmailId) {
		this.cEmailId = cEmailId;
	}
	public List<Address> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
	
	

}
