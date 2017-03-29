package com.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.domain.Address;
import com.jdbc.domain.Employee;

public class EmployeeAddressRepository {

	public static void main(String[] args) {
		EmployeeAddressRepository employeeAddressRepository = new EmployeeAddressRepository();

		String empid = "12";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Employee e = null;

		try {
			con = DataBaceConnectionUtil.getConnection();
			e = employeeAddressRepository.getEmployeeByEmpid(empid, con, pstmt, rs);
			List<String> zipcodes = employeeAddressRepository.getZipcodesByEmpid(empid, con, pstmt, rs);
			List<Address> addresses = employeeAddressRepository.getAddressesbyZipcodes(zipcodes, con, pstmt, rs);

			e.setAddressList(addresses);

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

	private Employee getEmployeeByEmpid(String empid, Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		pstmt = con.prepareStatement("select * from Employee where Empid=?");
		pstmt.setString(1, empid);
		rs = pstmt.executeQuery();
		Employee e = null;
		while (rs.next()) {
			e = new Employee();
			e.setEmpid(rs.getString("empid"));
			e.setEmpname(rs.getString("empname"));
			e.setEmpdesg(rs.getString("designation"));
		}
		return e;
	}

	private List<String> getZipcodesByEmpid(String empid, Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		pstmt = con.prepareStatement("select zipcode from EmployeeAddress where Empid=?");
		pstmt.setString(1, empid);
		rs = pstmt.executeQuery();
		List<String> zipcodes = new ArrayList<String>();
		while (rs.next()) {
			zipcodes.add(rs.getString("zipcode"));
		}
		return zipcodes;
	}

	private List<Address> getAddressesbyZipcodes(List<String> zipcodes, Connection con, PreparedStatement pstmt, ResultSet rs)
			throws SQLException {
		StringBuilder sb  = new StringBuilder("select * from Address where zipcode in(");
		String delimiter = "";
		for(int i=0;i<zipcodes.size();i++) {
			sb.append(delimiter).append("?");
			delimiter = ",";
		}
		sb.append(")");
		pstmt = con.prepareStatement(sb.toString());
		
		int i=1;
		for(String zipcode : zipcodes) {
			pstmt.setString(i, zipcode);
			i++;
		}
		rs = pstmt.executeQuery();
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

		System.out.println(e.getEmpid() + "," + e.getEmpname() + "," + e.getEmpdesg());

		List<Address> address = e.getAddressList();
		for (Address a1 : address) {
			System.out.println(a1.getZipcode() + "," + a1.getArea() + "," + a1.getStreet() + "," + a1.getDistrict());
		}
	}

}
