package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("name");
		
		// 为用户创建一个Session对象,有则使用，无则创建
		HttpSession session=request.getSession(true);
		
		session.setAttribute("name", name);
		
		// 防止关闭浏览器导致Session失效
		// 手动发送一个JSESSIONID的Cookie,并设置maxAge
		Cookie cookie=new Cookie("JSESSIONID",session.getId());
		cookie.setMaxAge(60*30);// 30分钟
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
		
		response.getWriter().write("success...");
		
	}

}
