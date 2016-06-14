package edu.uestc.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JndiDataSource {
	private volatile static JndiDataSource jndiDataSource;
	private static DataSource dataSource;
	
	private JndiDataSource() throws Exception{
		Context context = new InitialContext();
		Context jndiContainer = (Context)context.lookup("java:comp/env");
		dataSource = (DataSource)jndiContainer.lookup("jdbc/datasource");
	}
	
	public static JndiDataSource getInstance() throws Exception{
		if(jndiDataSource == null){
			synchronized(JndiDataSource.class){
				if(jndiDataSource == null){
					jndiDataSource = new JndiDataSource();
				}
			}
		}
		return jndiDataSource;
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
