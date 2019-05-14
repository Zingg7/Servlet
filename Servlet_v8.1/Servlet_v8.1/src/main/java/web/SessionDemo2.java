package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 有则使用，无则返回null
		HttpSession session=request.getSession(false);
		
		if(session==null) {
			response.getWriter().write("first visit");
		}else {
			String name=(String)session.getAttribute("name");
			response.getWriter().write("name="+name);
		}
	}
}
