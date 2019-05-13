package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
		HttpServletResponse response) throws ServletException, IOException {
		//发送重定向响应
		//response.setStatus(302); 
		//response.setHeader("Location", 
		//		"http://doc.tedu.cn");
		
		//sendRedirect与上述两行代码完全等价
		response.sendRedirect("http://doc.tedu.cn"); 
	}

}





