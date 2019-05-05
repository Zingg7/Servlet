package day02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理get请求参数
 * 当浏览器的get请求发送到tomcat的服务器时候,tomcat会解析请求头部,
 * 将请求头部数据存储到Request对象中传递到servlet,
 * 在servlet中可以利用getParamter方法获取请求参数
 */
public class TestServlet extends HttpServlet {
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
		doGet(request, response);
	}

}
