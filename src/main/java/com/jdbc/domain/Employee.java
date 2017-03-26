package com.jdbc.domain;

import java.util.List;

public class Employee {

	private String empid;
	private String empname;
	private String empdesg;
	
	private List<Address> addressList;

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getEmpdesg() {
		return empdesg;
	}

	public void setEmpdesg(String empdesg) {
		this.empdesg = empdesg;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
	

}
