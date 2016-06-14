package edu.uestc.DataSource;

public class Test01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int loop=0 ;loop<40; loop++){
			TestDbcpDataSource test = new TestDbcpDataSource();
			Thread t = new Thread(test);
			t.start();
		}
	}
}
