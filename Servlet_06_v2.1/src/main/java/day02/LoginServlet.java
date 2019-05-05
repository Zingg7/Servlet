package day02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在Servlet中接收表单参数
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取get请求参数时候,如果key对应的参数不存在,就返回null
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String pwd = request.getParameter("pwd");
		System.out.println("name: "+name);
		System.out.println("age: "+age);
		System.out.println("pwd: "+pwd);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.println("<h1>收到get参数</h1>");
		out.print("</body></html>");
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 用户浏览器发起的post请求被Tomcat接收
		 * Tomcat将请求信息解析到Request对象中
		 * 任何请求信息,都可以从Request对象中获取
		 * 如获取请求参数:request.getParameter("username")
		 */
		// setCharacterEncoding通知Request对象
		// 在解析 请求Body 时候使用UTF-8编码
		// 必须在  getParameter 方法调用之前设置
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("username");
		System.out.println("name: "+name);
		String pwd = request.getParameter("pwd");
		System.out.println("pwd: "+pwd);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		if (name.equals("tom")&&pwd.equals("123")) {
			out.println("<h1>登录成功</h1>");
		} else {
			out.println("<h1>登录失败</h1>");
		}
		out.print("</body></html>");
	}

}
