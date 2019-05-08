package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 删除名为time的Cookie
		Cookie cookie=new Cookie("time","");
		// 设置Cookie的路径
		cookie.setPath(request.getContextPath());
		// 设置Cookie的存活时间为0
		cookie.setMaxAge(0);
		
		response.addCookie(cookie);
	}

}
