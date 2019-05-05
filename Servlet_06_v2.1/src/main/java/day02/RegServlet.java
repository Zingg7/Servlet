package day02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegServlet
 */
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 在Servlet中获取多选(数组)参数
		request.setCharacterEncoding("UTF-8");
		String[] hobby=request.getParameterValues("hobby");
		System.out.println("hobby="+Arrays.toString(hobby));
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<html><body>");
		out.println("<h1>收到多选参数</h1>");
		out.println("</body></html>");
	}

}
