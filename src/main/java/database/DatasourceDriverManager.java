package database;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatasourceDriverManager {
	private Map<String, DatasourcePool> pools;
	private volatile static DatasourceDriverManager datasourceDriverManager;
	
	private DatasourceDriverManager() throws FileNotFoundException, SQLException{
		pools = new HashMap<String, DatasourcePool>();
		DatasourceConfig datasourceConfig = new DatasourceConfig();
		for(DatasourceConfigBean datasourceConfigBean : datasourceConfig.getDatasourceConfigBeanList()){
			pools.put(datasourceConfigBean.getDatabaseType(), new DatasourcePool(datasourceConfigBean));
		}
	}
	
	public static DatasourceDriverManager getInstance() throws FileNotFoundException, SQLException{
		if(datasourceDriverManager == null){
			synchronized(DatasourceDriverManager.class){
				if(datasourceDriverManager == null){
					datasourceDriverManager = new DatasourceDriverManager();
				}
			}
		}
		return datasourceDriverManager;
	}
	
	public Connection getConnection(String poolName) throws SQLException{
		return pools.get(poolName).getConnection();
	}
	
	public DatasourcePool getDatasourcePool(String poolName){
		return pools.get(poolName);
	}
}
