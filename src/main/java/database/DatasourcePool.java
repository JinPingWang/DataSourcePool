package database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class DatasourcePool implements DataSource {
	BlockingQueue<Connection> blockQueue;
	List<Connection> list;
	
	public DatasourcePool(DatasourceConfigBean datasourceConfigBean) throws SQLException{
		blockQueue = new ArrayBlockingQueue<Connection>(datasourceConfigBean.getMaxConnectionCount());
		list = new ArrayList<Connection>();
		for(int loop=0; loop<datasourceConfigBean.getInitialSize(); loop++){
			Connection conn = DriverManager.getConnection(datasourceConfigBean.getUrl(),
					datasourceConfigBean.getUsername(), datasourceConfigBean.getPassword());
			blockQueue.add(conn);
			list.add(conn);
		}
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		try {
			return blockQueue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void freeConnection(Connection conn){
		try {
			blockQueue.put(conn);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close() throws SQLException{
		for(int loop=0; loop<list.size(); loop++){
			list.get(loop).close();
			list.set(loop, null);
		}
	}
	
	public void close(boolean flag) throws Exception{
		if(blockQueue.size() == list.size()){
			close();
		}
		else{
			throw new Exception("some Connection is using");
		}
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
		
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
