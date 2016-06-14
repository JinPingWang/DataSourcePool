package edu.uestc.DataSource;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DbcpDataSource {
	private volatile static DbcpDataSource dbcpDataSource;
//	private static DataSource dataSource;
	private static BasicDataSource dataSource;
	
	private DbcpDataSource() throws Exception{
		InputStream in = new FileInputStream(DbcpDataSource.class.getResource("/dbcp.properties").getPath());
		Properties property = new Properties();
		property.load(in);
//		dataSource = BasicDataSourceFactory.createDataSource(property);
		dataSource = (BasicDataSource)BasicDataSourceFactory.createDataSource(property);
	}
	
	public static DbcpDataSource getInstance() throws Exception{
		if(dbcpDataSource == null){
			synchronized(DbcpDataSource.class){
				if(dbcpDataSource == null){
					dbcpDataSource = new DbcpDataSource();
				}
			}
		}
		return dbcpDataSource;
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
