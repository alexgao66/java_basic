package com.alex.j2se.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 测试fetchSize对查询效率的影响
 * @author alex
 *
 */
public class FetchSizeTest {
	
	public static void main(String[] args) throws SQLException {
		int total = 1000;
		/*queryWithFetchSize(total,50);
		queryWithFetchSize(total,100);
		queryWithFetchSize(total,500);
		queryWithFetchSize(total,1000);*/
		System.out.println("#######################################");
		total = 10000;
		queryWithFetchSize(total,50);
		queryWithFetchSize(total,100);
		queryWithFetchSize(total,500);
		queryWithFetchSize(total,800);
		queryWithFetchSize(total,1000);
		queryWithFetchSize(total,2000);
	}
	
	public static void queryWithFetchSize(int total,int fetchSize) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			long start = System.currentTimeMillis();
			conn = JDBCBasicTest.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"select l.subsidize_name, l.student_name, l.csrq, l.yfje from zz_free_fund_list l where rownum <= ?");
			ps.setFetchSize(fetchSize);
			ps.setInt(1, total);
			rs = ps.executeQuery();
			while(rs.next()) {
				rs.getString("student_name");
				rs.getDate("csrq");
				rs.getInt(4);
			}
			System.out.println("total size: " + total + " fetch size: " + fetchSize + 
					" -------------time:" + (System.currentTimeMillis() - start));
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
}
