package com.jdbc.repository.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.domain.Customer;
import com.jdbc.util.DataBaceConnectionUtil;

public class CustomerRepository {
	public static void main(String[] args) {
		CustomerRepository customerRepository = new CustomerRepository();
		//customerRepository.createCustomerTableWithStmt();
		
		//customerRepository.insertValuesIntoCustomerTableWithStmt();
		
		//List<Customer> customerList1 = customerRepository.retrieveCustomersDetailsWithStmt();
		//customerRepository.printCustomer(customerList1);
		
		//Customer customer =customerRepository.getCustomerDetailsByEmpidWithStmt(102);
		//System.out.println(customer.getcId() + "  " + customer.getcName() + "  " + customer.getcEmailId());
		
		//customerRepository.createCustomerTableWithPstmt(106,"ranjith","ranjith@gmail.com");
		
		//List<Customer> customerList2 = customerRepository.retrieveCustomersDetailsWithPstmt();
		//customerRepository.printCustomer(customerList2);
		
		Customer customer =customerRepository.getCustomerDetailsByEmpidWithPstmt(104);
		System.out.println(customer.getcId() + " \n " + customer.getcName() + " \n " + customer.getcEmailId());
	}


	public void printCustomer(List<Customer> customerList2) {
		for(Customer customer:customerList2){
			System.out.println(customer.getcId() + "  " + customer.getcName() + "  " + customer.getcEmailId());
			
		}
	}




	private void createCustomerTableWithStmt() {
		Connection con=null;
		Statement stmt=null;
		try{
			con=DataBaceConnectionUtil.getConnection();
			stmt=con.createStatement();
			
			StringBuilder builder=new StringBuilder("create table Customer(");
			builder.append("cId").append("  ").append("number(10)").append("  ").append("primary key").append(",")
			.append("cName").append("  ").append("varchar2(10)").append(" ").append("not null").append(",")
			.append("cEmailId").append("  ").append("varchar2(50)").append(" ").append("not null").append(")");
			
			int i= stmt.executeUpdate(builder.toString());
			System.out.println(i+"  table is created");
		}catch(Exception ex){
			ex.printStackTrace();
			
		}finally{
			try {
				DataBaceConnectionUtil.closeStmt(stmt);
				DataBaceConnectionUtil.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	private void insertValuesIntoCustomerTableWithStmt() {
		Connection con=null;
		Statement stmt=null;
		int cId=105;
		String cName="prasanna";
		String cEmailId="prasanna@gmail.com";
		try{
			con=DataBaceConnectionUtil.getConnection();
			stmt=con.createStatement();
			
			StringBuilder builder=new StringBuilder("insert into Customer(cId,cName,cEmailId) values(");
			builder.append(cId).append(",")
			.append("'").append(cName).append("'").append(",")
			.append("'").append(cEmailId).append("'").append(")");
			
			int i = stmt.executeUpdate(builder.toString());
			System.out.println(i + "   rows inserted");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			try {
			DataBaceConnectionUtil.closeStmt(stmt);
			DataBaceConnectionUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		}
	private List<Customer> retrieveCustomersDetailsWithStmt() {

		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		List<Customer> customerDetails = new ArrayList<Customer>();
		try{
			con=DataBaceConnectionUtil.getConnection();
			stmt=con.createStatement();
			String sql= "select cId,cName,cEmailId from Customer";
			rs=stmt.executeQuery(sql);
			
			Customer customer=null;
			while(rs.next()){
				customer=new Customer();
				customer.setcId(rs.getInt("cId"));
				customer.setcName(rs.getString("cName"));
				customer.setcEmailId(rs.getString("cEmailId"));
				customerDetails.add(customer);
				
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				DataBaceConnectionUtil.closeStmt(stmt);
				DataBaceConnectionUtil.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return customerDetails;
	}
	private Customer getCustomerDetailsByEmpidWithStmt(int cId) {
    Connection con = null;
    Statement stmt=null;
    ResultSet rs=null;
    Customer customer=null;
	try{
		con=DataBaceConnectionUtil.getConnection();
		stmt=con.createStatement();
		String sql="select * from Customer where cId="+"'"+cId+"'"  ;
		rs=stmt.executeQuery(sql);
		
		while(rs.next()){
			customer= new Customer();
			
			customer.setcId(rs.getInt("cId"));
			customer.setcName(rs.getString("cName"));
			customer.setcEmailId(rs.getString("cEmailId"));
		}
	
	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
		try {
			DataBaceConnectionUtil.closeStmt(stmt);
			DataBaceConnectionUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	return customer;
    }
	private void createCustomerTableWithPstmt(int cId, String cName, String cEmailId) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DataBaceConnectionUtil.getConnection();
			pstmt=con.prepareStatement("insert into Customer(cId,cName,cEmailId)values(?,?,?)");
			pstmt.setInt(1,cId);
			pstmt.setString(2,cName);
			pstmt.setString(3,cEmailId);
			
			int i =pstmt.executeUpdate();
			System.out.println(i + "  row inserde.");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				DataBaceConnectionUtil.closePstmt(pstmt);
				DataBaceConnectionUtil.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	private List<Customer> retrieveCustomersDetailsWithPstmt() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Customer> customerDetails= new ArrayList<Customer>();
		try{
			con=DataBaceConnectionUtil.getConnection();
			pstmt = con.prepareStatement("select cId,cName,cEmailId from Customer");
			rs = pstmt.executeQuery();
			Customer customer=null;
			while (rs.next()) {
				customer=new Customer();
				
				customer.setcId(rs.getInt("cId"));
				customer.setcName(rs.getString("cName"));
				customer.setcEmailId(rs.getString("cEmailId"));
				
				customerDetails.add(customer);
			}
		}
		catch (ClassNotFoundException e) {
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
		return customerDetails;
	}
	private Customer getCustomerDetailsByEmpidWithPstmt(int cId) {
		
		Connection con = null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    Customer customer=null;
		try{
			con=DataBaceConnectionUtil.getConnection();
			pstmt=con.prepareStatement("select * from Customer where cId=?" );
			pstmt.setInt(1,cId);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				customer= new Customer();
				
				customer.setcId(rs.getInt("cId"));
				customer.setcName(rs.getString("cName"));
				customer.setcEmailId(rs.getString("cEmailId"));
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				DataBaceConnectionUtil.closePstmt(pstmt);
				DataBaceConnectionUtil.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return customer;
	}
}

	

	

	
	


	


	

