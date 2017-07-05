package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uestc.DataSource.TestJndiDataSource;

public class Controller extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{

		System.out.println("test");
		/**
		 * 3：将图片信息写回客户端
		 */
		for(int loop=0 ;loop<1000; loop++){
			TestJndiDataSource test = new TestJndiDataSource();
			Thread t = new Thread(test);
			t.start();
		}
		
//		response.setContentType("application/json;charSet=utf-8");
//		PrintWriter out =response.getWriter();
//		out.write("");
//		out.flush();
//		out.close();
	}
}
