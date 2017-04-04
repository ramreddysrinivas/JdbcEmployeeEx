package com.jdbc.repository.address;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.domain.Address;
import com.jdbc.util.DataBaceConnectionUtil;

public class AddressRepository {

	public static void main(String[] args) {

		AddressRepository address = new AddressRepository();
		// address.insertWithStatement();
		// List<Address> empList1 = address.retrieveEmployeesWithStmt();
		// address.insertWithPreparedStatement();
		// List<Address> empList1 = address.retrieveEmployeesWithPstmt();
		Address a1 = address.getAddressByZipcodeWithPstmt("500072");
		System.out.println(a1.getZipcode() + "," + a1.getArea() + ", " + a1.getStreet() + "," + a1.getDistrict());
		// Address a2 = address.getAddressByZipcodeWithStatement("503225");
		// System.out.println(a2.getZipcode() + " , " + a2.getStreet() + ", " +
		// a2.getArea() + ", " + a2.getDistrict());

		// address.printEmployees(empList1);
	}

	private void printEmployees(List<Address> empList1) {
		for (Address i : empList1) {
			System.out.println(i.getZipcode() + "," + i.getStreet() + ", " + i.getArea() + ", " + i.getDistrict());
		}
	}

	private List<Address> retrieveEmployeesWithStmt() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Address> emps = new ArrayList<Address>();
		try {
			con = DataBaceConnectionUtil.getConnection();
			stmt = con.createStatement();
			String sql = "select zipcode,street,area,district from Address";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Address e = new Address();
				e.setZipcode( rs.getString("zipcode"));
				e.setStreet(rs.getString("street"));
				e.setArea(rs.getString("area"));
				e.setDistrict(rs.getString("district"));
				
				emps.add(e);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DataBaceConnectionUtil.closeRs(rs);
				DataBaceConnectionUtil.closeStmt(stmt);
				DataBaceConnectionUtil.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emps;
	}

	private Address getAddressByZipcodeWithStatement(String zipcode) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Address a = null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			stmt = con.createStatement();
			String sql = "select * from Address where zipcode = " + "'" + zipcode + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				a = new Address();
				a.setZipcode(rs.getString("zipcode"));
				a.setStreet(rs.getString("street"));
				a.setArea(rs.getString("area"));
				a.setDistrict(rs.getString("district"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataBaceConnectionUtil.closeStmt(stmt);
				DataBaceConnectionUtil.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}

	private void insertWithPreparedStatement() {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			pstmt = con.prepareStatement("insert into Address(zipcode,street,area,district) values(?,?,?,?)");
			pstmt.setString(1, "500038");
			pstmt.setString(2, "mg colony");
			pstmt.setString(3, "sr nagar");
			pstmt.setString(4, "rangareddy");
			int i = pstmt.executeUpdate();
			System.out.println(i + "   rows inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				DataBaceConnectionUtil.closePstmt(pstmt);
				DataBaceConnectionUtil.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void insertWithStatement() {
		Connection con = null;
		Statement stmt = null;
		String zipcode = "500072";
		String street = "viveknagar";
		String area = "kukatpally";
		String district = "medchal";
		try {
			con = DataBaceConnectionUtil.getConnection();
			stmt = con.createStatement();
			StringBuilder builder = new StringBuilder("insert into Address(zipcode,street,area,district) values(");
			builder.append("'").append(zipcode).append("'").append(",");
			builder.append("'").append(street).append("'").append(",");
			builder.append("'").append(area).append("'").append(",");
			builder.append("'").append(district).append("'").append(")");

			int i = stmt.executeUpdate(builder.toString());
			System.out.println(i + "   rows inserted");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DataBaceConnectionUtil.closeStmt(stmt);
				DataBaceConnectionUtil.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private List<Address> retrieveEmployeesWithPstmt() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Address> emps = new ArrayList<Address>();
		try {
			con = DataBaceConnectionUtil.getConnection();
			pstmt = con.prepareStatement("select zipcode,street,area,district from Address");
			ResultSet rs1 = pstmt.executeQuery();
			while (rs1.next()) {
				Address e = new Address();
				e.setZipcode(rs1.getString("zipcode"));
				e.setStreet(rs1.getString("street"));
				e.setArea(rs1.getString("area"));
				e.setDistrict(rs1.getString("district"));
				emps.add(e);
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DataBaceConnectionUtil.closeRs(rs);
				DataBaceConnectionUtil.closePstmt(pstmt);
				DataBaceConnectionUtil.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emps;
	}

	private Address getAddressByZipcodeWithPstmt(String zipcode) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Address a = null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			pstmt = con.prepareStatement("select * from Address where zipcode=?");
			pstmt.setString(1, zipcode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				a = new Address();
				a.setZipcode(rs.getString("zipcode"));
				a.setStreet(rs.getString("street"));
				a.setArea(rs.getString("area"));
				a.setDistrict(rs.getString("district"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataBaceConnectionUtil.closeRs(rs);
				DataBaceConnectionUtil.closePstmt(pstmt);
				DataBaceConnectionUtil.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}
}
