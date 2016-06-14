package edu.uestc.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0DataSource {
	private volatile static C3p0DataSource c3p0DataSource;
	private static DataSource dataSource;
	
	private C3p0DataSource() throws Exception{
		dataSource = new ComboPooledDataSource("mysql");
	}
	
	public static C3p0DataSource getInstance() throws Exception{
		if(c3p0DataSource == null){
			synchronized(C3p0DataSource.class){
				if(c3p0DataSource == null){
					c3p0DataSource = new C3p0DataSource();
				}
			}
		}
		return c3p0DataSource;
	}
	
	public Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	public void close(Connection conn, Statement stat, ResultSet rs) {
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;
		}
		
		if(stat != null){
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stat = null;
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = null;
		}
	}
}
