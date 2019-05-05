package day01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 继承HttpServlet,比实现Servlet接口简单
 * 相比GenericServlet,可以区别get,post请求
 * 重写doGet就是处理get请求
 * 	 get请求: 浏览器地址栏直接请求时get请求
 *           a标签连接请求时get请求
 *  	     img标签中的src是get请求
 * 重写doPost就是处理post请求
 *   post请求: 表单method=post时候的请求
 */
public class TestServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("HI HttpServlet doGet()");
	}
}
