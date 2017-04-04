package com.jdbc.repository.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.domain.Employee;
import com.jdbc.util.DataBaceConnectionUtil;

public class EmployeeRepository {

	public static void main(String[] args) {

		EmployeeRepository employeeInsert = new EmployeeRepository();
		// employeeInsert.insertWithStatement();
		
		//List<Employee> empList1 = employeeInsert.retrieveEmployeesWithStmt();
		
		//employeeInsert.printEmployees(empList1);
		Employee e1 = employeeInsert.getEmployeeByEmpidWithStmt("12");
		System.out.println( e1.getEmpid() + " , " +e1.getEmpname() + " , " + e1.getEmpdesg());
		
		// Employee e2 = employeeInsert.getEmployeeByEmpidWithPstmt("13");
		// System.out.println( e2.getEmpid() + " , " +e2.getEmpname() + " , " + e2.getEmpdesg());
		
		//List<Employee> empList2 = employeeInsert.selectMulitipleColumnsFromEmployeeWithStmt();
		//employeeInsert.printEmployees(empList2);
		
		//employeeInsert.updateEmployeeNameUsingEmpidWithStatement("Srinu","10");
		
		// employeeInsert.updateEmployeeDetailsForEmpidWithStatement("charan", "developer", "10");
		
		// employeeInsert.deleteEmployeeForEmpidWithStmt("16");
		
		//employeeInsert.insertWithPreparedStatement();
		
		// List<Employee> empList3 =employeeInsert.retrieveEmployeesWithPstmt();
		
		//employeeInsert.printEmployees(empList3);
		
		//List<Employee> empList4 = employeeInsert.getEmployeesusingEmpNameWithPstmt("srinu");
		//employeeInsert.printEmployees(empList4);
		
		 //employeeInsert.updateEmpNameUsingEmpIdWithPrepareStatement("nag" , "13");
		
		// employeeInsert.updateEmployeeUsingEmpIdWithPrepareStatement("srinu" , "developer" ,"12");
		
		//employeeInsert.deleteEmployeeUsingEmpIdWithPstmt("11");
		
		
	}
	private void printEmployees(List<Employee> empList1) {
		for (Employee i : empList1) {
			System.out.println(i.getEmpid() + "," + i.getEmpname() + ", " + i.getEmpdesg());
		}
	}
	private void insertWithStatement() {
		Connection con = null;
		Statement stmt = null;
		String empId = "13";
		String empName = "nag";
		String desg = "civil eng";
		try {
			con = DataBaceConnectionUtil.getConnection();
			stmt = con.createStatement();
			
			StringBuilder builder = new StringBuilder("insert into Employee(empid,empname,designation) values(");
			builder.append("'").append(empId).append("'").append(",");
			builder.append("'").append(empName).append("'").append(",");
			builder.append("'").append(desg).append("'").append(")");
			
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
	private List<Employee> retrieveEmployeesWithStmt() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> emps = new ArrayList<Employee>();
		try {
			con = DataBaceConnectionUtil.getConnection();
			stmt = con.createStatement();
			String sql = "select empid,empname,designation from employee";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Employee e = new Employee();
				/*String empid = rs.getString("empid");
				String empname = rs.getString("empName");
				String desg = rs.getString("designation");
				e.setEmpid(empid);
				e.setEmpname(empname);
				e.setEmpdesg(desg);*/
				
				//we can write above commented code as below.
				
				e.setEmpid(rs.getString("empid"));
				e.setEmpname(rs.getString("empName"));
				e.setEmpdesg(rs.getString("designation"));
				
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
	private Employee getEmployeeByEmpidWithStmt(String empid) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Employee e = null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			stmt = con.createStatement();
			String sql = "select * from Employee where empid=" + "'" + empid + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				e = new Employee();
				
				e.setEmpid(rs.getString("empid"));
				e.setEmpname(rs.getString("empName"));
				e.setEmpdesg(rs.getString("designation"));
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
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
	private List<Employee> selectMulitipleColumnsFromEmployeeWithStmt() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> emps = new ArrayList<Employee>();
		try {
			con = DataBaceConnectionUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select empid,empname from employee");
			while (rs.next()) {
				Employee e = new Employee();
				String empid = rs.getString("empid");
				String empname = rs.getString("empName");
				// String desg = rs.getString("designation");
				e.setEmpid(empid);
				e.setEmpname(empname);
				// e.setEmpdesg(desg);
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
	private void updateEmployeeNameUsingEmpidWithStatement(String empName, String empId) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			stmt = con.createStatement();
			String sql = "update Employee set empname=" + "'" + empName + "'" + " where empid=" + "'" + empId + "'" ;
			int i = stmt.executeUpdate(sql);
			System.out.println(i + "   rows updated ");
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
	private void updateEmployeeDetailsForEmpidWithStatement(String empName, String designation, String empId ) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			stmt = con.createStatement();
			String sql = "update Employee set empname=" + "'" + empName + "'" + "," + " designation =" + "'" + designation +"'" +
				"  where  empid=" + "'" + empId + "'";
			int i = stmt.executeUpdate(sql);
			System.out.println(i + "   rows updated ");
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
	private void deleteEmployeeForEmpidWithStmt(String empid) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			stmt = con.createStatement();
			String sql = "delete from employee where empid=" +  "'" + empid + "'";
			int i = stmt.executeUpdate(sql);
			System.out.println(i + "   rows deleted ");
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
	private void insertWithPreparedStatement() {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			pstmt = con.prepareStatement("insert into Employee(empid,empname,designation) values(?,?,?)");
			pstmt.setString(1, "11");
			pstmt.setString(2, "kiran");
			pstmt.setString(3, "sr software");
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
	private List<Employee> retrieveEmployeesWithPstmt() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Employee> emps = new ArrayList<Employee>();
		try {
			con = DataBaceConnectionUtil.getConnection();
			pstmt = con.prepareStatement("select empid,empname,designation from employee");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Employee e = new Employee();
				e.setEmpid(rs.getString("empid"));
				e.setEmpname(rs.getString("empName"));
				e.setEmpdesg(rs.getString("designation"));
				emps.add(e);
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
		return emps;
	}
	private Employee getEmployeeByEmpidWithPstmt(String empid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Employee e = null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			pstmt = con.prepareStatement("select * from employee where empid=?");
			pstmt.setString(1, empid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				e = new Employee();
				e.setEmpid(rs.getString("empid"));
				e.setEmpname(rs.getString("empName"));
				e.setEmpdesg(rs.getString("designation"));
			}
		}
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
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
		return e;
	}
	private List<Employee> getEmployeesusingEmpNameWithPstmt(String empName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Employee> emps = new ArrayList<Employee>();
		try {
			con = DataBaceConnectionUtil.getConnection();
			pstmt = con.prepareStatement("select * from employee where empname=?");
			pstmt.setString(1, empName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
	private void updateEmpNameUsingEmpIdWithPrepareStatement(String empName,String empId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			pstmt = con.prepareStatement("update employee set empname= ? where empid= ?" );
			pstmt.setString(1,empName);
			pstmt.setString(2,empId);
			int i = pstmt.executeUpdate();
			System.out.println(i + "   rows updated");
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
	private void updateEmployeeUsingEmpIdWithPrepareStatement(String empName, String designation ,String empid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			pstmt = con.prepareStatement("update Employee set empname=?,designation=? where  empid=?");
			pstmt.setString(1, empName);
			pstmt.setString(2, designation);
			pstmt.setString(3, empid);
			int i = pstmt.executeUpdate();
			System.out.println(i + "   rows updated");
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
	private void deleteEmployeeUsingEmpIdWithPstmt(String empid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DataBaceConnectionUtil.getConnection();
			pstmt = con.prepareStatement("delete from employee where empid=?");
			pstmt.setString(1, empid);
			int i = pstmt.executeUpdate();
			System.out.println(i + "   rows deleted");
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
}
