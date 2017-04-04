package com.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaceConnectionUtil {

	private DataBaceConnectionUtil() {

	}
	public static Connection getConnection() throws ClassNotFoundException,SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "test", "test");
	}
	public static void closeStmt(Statement stmt) throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
	}
	public static void closeConnection(Connection con) throws SQLException {
		if (con != null) {
			con.close();
		}
	}
	public static void closeRs(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}
	public static void closePstmt(PreparedStatement pstmt) throws SQLException {
		if (pstmt != null) {
			pstmt.close();
		}
	}
}
