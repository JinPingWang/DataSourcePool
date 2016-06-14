package edu.uestc.DataSource;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import database.DatasourceDriverManager;

public class TestDatasourceDriverManager {

	public static void main(String[] args) throws FileNotFoundException, SQLException, InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();   
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<Integer>> list = new ArrayList<Future<Integer>>();
		
		//运行时间：2306毫秒	5000
		//运行时间：510毫秒	400
		for(int loop=0; loop<400; loop++){
			Test041 test041 = new Test041();
			FutureTask task = new FutureTask(test041);
			list.add((Future<Integer>) executorService.submit(task));
		}
		
		
		//Data source rejected establishment of connection,  message from server: "Too many connections"
		
		//The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.
		//Communications link failure
		
		//运行时间：3651毫秒
//		for(int loop=0; loop<400; loop++){
//			Test042 test042 = new Test042();
//			FutureTask task = new FutureTask(test042);
//			list.add((Future<Integer>) executorService.submit(task));
//		}
		

		for(Future<Integer> future : list){
			future.get();
		}
		DatasourceDriverManager.getInstance().getDatasourcePool("Mysql").close();
		
		executorService.shutdown();
		long end = System.currentTimeMillis();        
	      System.out.println("运行时间："+(end-start)+"毫秒"); 
	}

}

class Test041 implements Callable<Integer>{
	public Integer call(){
		try{
			Connection conn = DatasourceDriverManager.getInstance().getConnection("Mysql");
			String sql = "select * from robot";
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString(1)+"\t"+rs.getString(2));
			}
			rs.close();
			stat.close();
			DatasourceDriverManager.getInstance().getDatasourcePool("Mysql").freeConnection(conn);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return 1;
	}
}

class Test042 implements Callable<Integer>{
	public Integer call(){
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.91.201/robot", "robot", "robot");
			String sql = "select * from robot";
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString(1)+"\t"+rs.getString(2));
			}
			rs.close();
			stat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return 1;
	}
}
