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
		//List<Employee> empList1		= employeeInsert.retrieveEmployeesWithStmt();
		Employee e1 = employeeInsert.getEmployeeByEmpidWithStmt("12");
		System.out.println( e1.getEmpid() + " , "  +e1.getEmpname() + " , "  +  e1.getEmpdesg());
		Employee e2 = employeeInsert.getEmployeeByEmpidWithPstmt("13");
		System.out.println( e2.getEmpid() + " , "  +e2.getEmpname() + " , "  +  e2.getEmpdesg());
		//List<Employee> empList1		= employeeInsert.selectMulitipleFromEmployeesWithStmt();
		//employeeInsert.updateWithStatementOneRow();
		//employeeInsert.updateWithStatementMultipleRows();
		//employeeInsert.deleteWithStmt();
		//employeeInsert.insertWithPreparedStatement();
		//List<Employee> empList1		= employeeInsert.retrieveEmployeesWithPstmt();
		//List<Employee> empList1		= employeeInsert.selectMulitipleFromEmployeesWithPstmt();
		//employeeInsert.updateWithPrepareStatementOneRow();
		//employeeInsert.updateWithPrepareStatementMultipleRows();
		//employeeInsert.deleteWithPstmt();
	    // employeeInsert.printEmployees(empList1);

	}

	private void printEmployees(List<Employee> empList1) {
		for (Employee i : empList1) {
			System.out.println(i.getEmpid() + "," + i.getEmpname() + ", " + i.getEmpdesg());
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

	private List<Employee> retrieveEmployeesWithStmt() {
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
	private Employee getEmployeeByEmpidWithStmt(String empid) {

		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		Employee e=null;
	/*List<Employee> emps = new ArrayList<Employee>();*/
		//Employee e1 = new Employee();
		try {
			con = DataBaceConnectionUtil.getConnection();
			stmt = con.createStatement();
			String sql = "select * from Employee where empid=" + "'" + empid + "'";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				e = new Employee();
			 e.setEmpid(rs.getString("empid"));
			 e.setEmpname( rs.getString("empName"));
			 e.setEmpdesg(rs.getString("designation"));
			}
			
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally{
			try {
				DataBaceConnectionUtil.closeRs(rs);
				DataBaceConnectionUtil.closeStmt(stmt);
				DataBaceConnectionUtil.closeConnection(con);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			 
		}
		return e;
		
	}
	
	private List<Employee> selectMulitipleFromEmployeesWithStmt() {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		List<Employee> emps = new ArrayList<Employee>();
		try {
			con = DataBaceConnectionUtil.getConnection();
			stmt = con.createStatement();
			String sql = "select empid,empname from employee";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Employee e = new Employee();
			String empid = rs.getString("empid");
			String empname = rs.getString("empName");
			//String desg = rs.getString("designation");
			e.setEmpid(empid);
			e.setEmpname(empname);
			//e.setEmpdesg(desg);
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

	private void updateWithStatementOneRow() {
		Connection con=null;
		Statement stmt=null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			   stmt = con.createStatement();
			   String sql  = "update Employee set empname='srinu' where empid='10'" ;
			  int i=stmt.executeUpdate(sql);
			  System.out.println(i  +   "   rows updated ");
			  
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
	private void updateWithStatementMultipleRows() {
		Connection con=null;
		Statement stmt=null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			   stmt = con.createStatement();
			   String sql  = "update Employee set empname='charan',designation='developer' where empid='10'" ;
			  int i=stmt.executeUpdate(sql);
			  System.out.println(i  +   "   rows updated ");
			  
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

	private void deleteWithStmt() {
		Connection con=null;
		Statement stmt=null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			   stmt = con.createStatement();
			   String sql  = "delete from employee where empid='10'" ;
			  int i=stmt.executeUpdate(sql);
			  System.out.println(i  +   "   rows deleted ");
			  
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

	
	private List<Employee> retrieveEmployeesWithPstmt() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Employee> emps = new ArrayList<Employee>();
		try {
			con =DataBaceConnectionUtil.getConnection();
			pstmt = con.prepareStatement("select empid,empname,designation from employee" );
			
			rs = pstmt.executeQuery();
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
			}
			
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
	
	private Employee getEmployeeByEmpidWithPstmt(String empid) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Employee e=null; 
		try {
			con =DataBaceConnectionUtil.getConnection();
			pstmt = con.prepareStatement("select * from employee where empid=?" );
			pstmt.setString(1,empid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				e = new Employee();
				 e.setEmpid(rs.getString("empid"));
				 e.setEmpname( rs.getString("empName"));
				 e.setEmpdesg(rs.getString("designation"));
			}
			}
			
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally{
			try {
				
				DataBaceConnectionUtil.closeRs(rs);
				DataBaceConnectionUtil.closePstmt(pstmt);
				DataBaceConnectionUtil.closeConnection(con);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return e;
	}

	private List<Employee> selectMulitipleFromEmployeesWithPstmt() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Employee> emps = new ArrayList<Employee>();
		try {
			con =DataBaceConnectionUtil.getConnection();
			pstmt = con.prepareStatement("select * from employee where empid=? and empname=?" );
			pstmt.setString(1,"11");
			pstmt.setString(2,"prasanna");
			rs = pstmt.executeQuery();
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
			}
			
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
	private void updateWithPrepareStatementOneRow() {
		Connection con =null;
		PreparedStatement pstmt=null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			pstmt= con.prepareStatement("update employee set empname=? where empid=?");
			 pstmt.setString(1,"prasanna");
			 pstmt.setString(2,"12");
			 int i = pstmt.executeUpdate();
			 System.out.println(i  +   "   rows updated");
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
	private void updateWithPrepareStatementMultipleRows() {
		Connection con =null;
		PreparedStatement pstmt=null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			pstmt= con.prepareStatement("update Employee set empname=?,designation=? where  empid=?");
			 pstmt.setString(1,"srinu");
			 pstmt.setString(2,"developer");
			 pstmt.setString(3,"12");
			 int i = pstmt.executeUpdate();
			 System.out.println(i  +   "   rows updated");
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
	private void deleteWithPstmt() {
		Connection con =null;
		PreparedStatement pstmt=null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			pstmt= con.prepareStatement("delete from employee where empid=?");
			 pstmt.setString(1,"11");
			 int i = pstmt.executeUpdate();
			 System.out.println(i  +   "   rows deleted");
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
		
	}

	

