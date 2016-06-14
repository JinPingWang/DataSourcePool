package edu.uestc.DataSource;

public class Test02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int loop=0 ;loop<20; loop++){
			TestC3p0DataSource test = new TestC3p0DataSource();
			Thread t = new Thread(test);
			t.start();
		}
	}
}
