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
		
		// Ϊ�û�����һ��Session����,����ʹ�ã����򴴽�
		HttpSession session=request.getSession(true);
		
		session.setAttribute("name", name);
		
		// ��ֹ�ر����������SessionʧЧ
		// �ֶ�����һ��JSESSIONID��Cookie,������maxAge
		Cookie cookie=new Cookie("JSESSIONID",session.getId());
		cookie.setMaxAge(60*30);// 30����
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
		
		response.getWriter().write("success...");
		
	}

}
