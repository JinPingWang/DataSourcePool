package database;

public class DatasourceConfigBean {
	private String databaseType;
	private String databasePoolName;
	private String driver;
	private String url;
	private String username;
	private String password;
	private int maxConnectionCount;
	private int initialSize;
	
	public DatasourceConfigBean(){

	}
	
	public DatasourceConfigBean(String databaseType, String databasePoolName, 
			String driver, String url, String username, String password, int maxConnectionCount){
		this.databaseType = databaseType;
		this.databasePoolName = databasePoolName;
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
		this.maxConnectionCount = maxConnectionCount;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public String getDatabasePoolName() {
		return databasePoolName;
	}

	public void setDatabasePoolName(String databasePoolName) {
		this.databasePoolName = databasePoolName;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMaxConnectionCount() {
		return maxConnectionCount;
	}

	public void setMaxConnectionCount(int maxConnectionCount) {
		this.maxConnectionCount = maxConnectionCount;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}
}
