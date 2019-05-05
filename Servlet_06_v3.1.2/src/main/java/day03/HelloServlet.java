package day03;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

/**
 * 演示Servlet到JSP协作转发
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) 
			throws ServletException, IOException {
		//将数据存储到request，用于与JSP共享数据
		req.setAttribute("msg", "Hello World!");
		
		//在JSP和Servlet协作处理时候，响应应该由JSP处理， Servelt中不要处理响应
		//resp.getWriter().write("Hello"); 
		//resp.flushBuffer();
		
		//RequestDispatcher 请求协作对象
		//创建对象时候需要提供一个目标页面的路径
		RequestDispatcher rd=req.getRequestDispatcher(
				"/WEB-INF/jsp/message.jsp");
		//调用forward方法转发到目标JSP继续执行
		//其中目标JSP与当前Servlet共享了同一对req, resp
		rd.forward(req, resp);
	}
}





