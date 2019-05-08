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
		// ɾ����Ϊtime��Cookie
		Cookie cookie=new Cookie("time","");
		// ����Cookie��·��
		cookie.setPath(request.getContextPath());
		// ����Cookie�Ĵ��ʱ��Ϊ0
		cookie.setMaxAge(0);
		
		response.addCookie(cookie);
	}

}
