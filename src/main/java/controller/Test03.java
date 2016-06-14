package controller;

import edu.uestc.DataSource.TestJndiDataSource;

public class Test03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int loop=0 ;loop<20; loop++){
			TestJndiDataSource test = new TestJndiDataSource();
			Thread t = new Thread(test);
			t.start();
		}
	}
}
