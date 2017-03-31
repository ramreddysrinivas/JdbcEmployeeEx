package com.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import org.apache.commons.collections4.CollectionUtils;

import com.jdbc.domain.Address;
import com.jdbc.domain.Employee;

public class EmployeeAddressRepository {

	public static void main(String[] args) {
		EmployeeAddressRepository employeeAddressRepository = new EmployeeAddressRepository();
		String empid = "15";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Employee e = null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			e = employeeAddressRepository.getEmployeeByEmpid(empid, con);
			List<String> zipcodes = employeeAddressRepository.getZipcodesByEmpid(empid, con);
			List<Address> addresses =null;
			//if(null != zipcodes && zipcodes.size()>0){
			if(CollectionUtils.isNotEmpty(zipcodes)){
				addresses= employeeAddressRepository.getAddressesbyZipcodes(zipcodes, con);
			}
			
          if(null != e){
        	  e.setAddressList(addresses);
          }
          employeeAddressRepository.printEmployeeDetails(e);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				DataBaceConnectionUtil.closeRs(rs);
				DataBaceConnectionUtil.closePstmt(pstmt);
				DataBaceConnectionUtil.closeConnection(con);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	private Employee getEmployeeByEmpid(String empid, Connection con) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement("select * from Employee where Empid=?");
		pstmt.setString(1, empid);
		ResultSet rs = pstmt.executeQuery();
		Employee e=null;
		while (rs.next()) {
			e = new Employee();
			e.setEmpid(rs.getString("empid"));
			e.setEmpname(rs.getString("empname"));
			e.setEmpdesg(rs.getString("designation"));
		}
		return e;
	}
	private List<String> getZipcodesByEmpid(String empid, Connection con) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement("select zipcode from EmployeeAddress where Empid=?");
		pstmt.setString(1, empid);
		ResultSet rs = pstmt.executeQuery();
		List<String> zipcodes = new ArrayList<String>();
		while (rs.next()) {
			zipcodes.add(rs.getString("zipcode"));
		}
		return zipcodes;
	}

	private List<Address> getAddressesbyZipcodes(List<String> zipcodes,Connection con)
			throws SQLException {
		StringBuilder sb = new StringBuilder("select * from Address where zipcode in(");
		String delimiter = "";
		for (int i = 0; i < zipcodes.size(); i++) {
			sb.append(delimiter).append("?");
			delimiter = ",";
		}
		sb.append(")");
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		int i = 1;
		for (String zipcode : zipcodes) {
			pstmt.setString(i, zipcode);
			i++;
		}
		 ResultSet rs = pstmt.executeQuery();
		List<Address> addresses = new ArrayList<Address>();
		Address a = null;
		while (rs.next()) {
			a = new Address();
			a.setZipcode(rs.getString("zipcode"));
			a.setArea(rs.getString("area"));
			a.setStreet(rs.getString("street"));
			a.setDistrict(rs.getString("district"));
			addresses.add(a);
		}
		return addresses;
	}

	private void printEmployeeDetails(Employee e) {
     if(null != e){
		System.out.println(e.getEmpid() + "," + e.getEmpname() + "," + e.getEmpdesg());
     
		List<Address> addressList = e.getAddressList();
		printAddresses(addressList);
     }else{
    	 System.out.println("Employee is not found.");
     }
	}
	private void printAddresses(List<Address> addressList) {
		//if(null != addressList ){
		if(CollectionUtils.isNotEmpty(addressList)){
		for (Address a1 : addressList) {
			System.out.println(a1.getZipcode() + "," + a1.getArea() + "," + a1.getStreet() + "," + a1.getDistrict());
		}
		}else{
			System.out.println("address not found.");
		}
	}
}
