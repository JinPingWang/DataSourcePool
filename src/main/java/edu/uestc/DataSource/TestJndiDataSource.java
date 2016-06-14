package edu.uestc.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestJndiDataSource implements Runnable{
	public void run() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			try {
				conn = JndiDataSource.getInstance().getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String sql = "select * from robot";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString(1)+"\t"+rs.getString(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				Thread.sleep((int)(Math.random()*300));
				JndiDataSource.getInstance().close(conn, stat, rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
