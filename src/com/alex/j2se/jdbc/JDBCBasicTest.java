package com.alex.j2se.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 学习JDBC基础知识
 * @author alex
 *
 */
public class JDBCBasicTest {
	
//	private static final String URL = "jdbc:oracle:thin:aa/123@192.168.21.236:1521:moepoc";
	
	private static final String URL = "jdbc:oracle:thin:xszzxm/xszz123@192.168.21.196:1521:orcl";
	
	public static void main(String[] args) throws SQLException {
		pagedQuery();
	}
	
	/**
	 * 获取总记录数
	 * @throws SQLException
	 */
	public static void getCount() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(1) from zz_free_fund_list");
			rs.next();
			int c = rs.getInt(1);
			System.out.println("count:"+c);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				rs.close();
				rs = null;
			}
			if(stmt != null) {
				stmt.close();
				stmt = null;
			}
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
	}
	
	/**
	 * 分页查询
	 * @throws SQLException
	 */
	public static void pagedQuery() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement("select l.subsidize_name,l.student_name,l.csrq,l.yfje from (select rownum rum,list.* from zz_free_fund_list list where rownum < ?) l where l.rum >= ?");
			ps.setInt(1, 40);
			ps.setInt(2, 20);
			rs = ps.executeQuery();
			System.out.println("stdName\tcsrq\tyfje\n----------------------------------");
			while(rs.next()) {
				String stdName = rs.getString("student_name");
				Date csrq = rs.getDate("csrq");
				int yfje = rs.getInt(4);
				System.out.println(stdName+"\t"+csrq+"\t"+yfje);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				rs.close();
				rs = null;
			}
			if(stmt != null) {
				stmt.close();
				stmt = null;
			}
			if(conn != null) {
				conn.close();
				conn = null;
			}
		}
	}
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn = null;
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    conn = DriverManager.getConnection(URL);
	    
	    return conn;
	}
}
