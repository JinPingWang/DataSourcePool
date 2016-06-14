package edu.uestc.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestC3p0DataSource implements Runnable{
	@Test
	public void run() {
		System.out.println("start");
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			try {
				conn = C3p0DataSource.getInstance().getConnection();
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
				DbcpDataSource.getInstance().close(conn, stat, rs);
				try {
//					int time = (int)(Math.random()*3000);
					int time = (int)(300000);
					
					Thread.sleep(time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
