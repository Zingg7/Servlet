package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 有则使用，无则创建
		HttpSession session=request.getSession();// 等同于getSession(true);
		
		Integer count=(Integer)session.getAttribute("count");
		
		if(count==null) {
			// 第一次
			count=1;
		}else {
			count++;
		}
		session.setAttribute("count", count);
		response.getWriter().write("count="+count);
	}

}
