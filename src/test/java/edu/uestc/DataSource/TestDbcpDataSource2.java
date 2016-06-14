package edu.uestc.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestDbcpDataSource2 {
	@Test
	public void test() {
		TestDbcpDataSource test1 = new TestDbcpDataSource();
		TestDbcpDataSource test2 = new TestDbcpDataSource();
		
		Thread t1 = new Thread(test1);
		Thread t2 = new Thread(test2);
		
		t1.start();
		t2.start();
	}
}
