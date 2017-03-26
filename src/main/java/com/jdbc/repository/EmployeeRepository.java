package com.jdbc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.domain.Employee;

public class EmployeeRepository {
	
	public static void main(String[] args) {
		
		EmployeeRepository employeeInsert = new EmployeeRepository();
		//employeeInsert.insertWithStatement();
		//employeeInsert.insertWithPreparedStatement();
		//List<Employee> empList1		= employeeInsert.retrieveEmployeesWithhStmt();
		List<Employee> empList1		= employeeInsert.retrieveEmployeesWithPstmt();
		//employeeInsert.printEmpoyees(empList1);
		
		employeeInsert.printEmployees(empList1);

	}

	private void printEmployees(List<Employee> empList1) {
		for (Employee i : empList1) {
			System.out.println(i.getEmpid() + "," + i.getEmpname() + ", " + i.getEmpdesg());
		}
	}

	private List<Employee> retrieveEmployeesWithhStmt() {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		List<Employee> emps = new ArrayList<Employee>();
		try {
			con = DataBaceConnectionUtil.getConnection();
			stmt = con.createStatement();
			String sql = "select empid,empname,designation from employee";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Employee e = new Employee();
			String empid = rs.getString("empid");
			String empname = rs.getString("empName");
			String desg = rs.getString("designation");
			e.setEmpid(empid);
			e.setEmpname(empname);
			e.setEmpdesg(desg);
			emps.add(e);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
	
	private void insertWithPreparedStatement() {
		Connection con =null;
		PreparedStatement pstmt=null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			pstmt= con.prepareStatement("insert into Employee(empid,empname,designation) values(?,?,?)");
			 pstmt.setString(1,"11");
			 pstmt.setString(2,"kiran");
			 pstmt.setString(3,"sr software");
			 int i = pstmt.executeUpdate();
			 System.out.println(i  +   "   rows inserted");
		} catch (SQLException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();	
		}finally{
			try {
				DataBaceConnectionUtil.closePstmt(pstmt);
				DataBaceConnectionUtil.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void insertWithStatement() {
		Connection con=null;
		Statement stmt=null;
		String empId="13";
		String empName="nag";
		String desg="civil eng";
		try {
			con = DataBaceConnectionUtil.getConnection();
			   stmt = con.createStatement();
			   
			   StringBuilder   builder = new StringBuilder("insert into Employee(empid,empname,designation) values(" );
			   builder.append("'").append( empId).append("'").append(",");
			   builder.append("'").append( empName).append("'").append(",");
			   builder.append("'").append(desg).append("'").append(")");
			  
			  int i=stmt.executeUpdate(builder.toString());
			  System.out.println(i  +   "   rows inserted");
			  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				DataBaceConnectionUtil.closeStmt(stmt);
				DataBaceConnectionUtil.closeConnection(con);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	

	
	
	private List<Employee> retrieveEmployeesWithPstmt() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Employee> emps = new ArrayList<Employee>();
		try {
			con =DataBaceConnectionUtil.getConnection();
			pstmt = con.prepareStatement("select empid,empname,designation from employee" );
			
			ResultSet rs1 = pstmt.executeQuery();
			while(rs1.next()){
			Employee e = new Employee();
			String empid = rs1.getString("empid");
			String empname = rs1.getString("empName");
			String desg = rs1.getString("designation");
			e.setEmpid(empid);
			e.setEmpname(empname);
			e.setEmpdesg(desg);
			emps.add(e);
		
			}
			}
			
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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

}
