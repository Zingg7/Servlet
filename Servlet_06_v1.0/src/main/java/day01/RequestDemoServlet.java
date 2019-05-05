package day01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestDemoServlet
 * 
 * 演示Request对象的功能
 * Request代表用户浏览器发送的请求信息
 */
public class RequestDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestDemoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 读取请求行上的Method信息
		String method = request.getMethod();
		// 读取请求行上的Request-URI
		String uri = request.getRequestURI();
		// 读取请求行上的协议版本
		String protocol = request.getProtocol();
		
		// 读取请求头信息
		// user-Agent 用户代理,就是浏览器的信息,类型版本等
		// 获取请求头中的用户浏览器相关信息
		String ua= request.getHeader("User-Agent");
		String host = request.getHeader("Host");
		
		// 设置服务器发送端的编码规则
		response.setCharacterEncoding("utf-8");
		// 设置浏览器接受时候的解码规则
		response.setContentType("text/html; charset=utf-8");
		
		// 设置ContentType的时候,response会自动设置CharacterEncoding 
		 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<body>");
		out.print("<ul>");
		out.print("<li>"+method+"</li>");
		out.print("<li>"+uri+"</li>");
		out.print("<li>"+protocol+"</li>");
		out.print("<li>"+ua+"</li>");
		out.print("</ul>");
		out.print("</body>");
		out.print("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
