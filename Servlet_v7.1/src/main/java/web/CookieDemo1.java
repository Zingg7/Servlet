package web;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ʵ�ּ�ס�û�������
		
		// ��ȡ�û����������û���
		String username=request.getParameter("name");
		// ���û���ͨ��Cookie���͸��û�
		// response.addHeader("set-cookie", "name="+username);
		Cookie cookie=new Cookie("name", URLEncoder.encode(username, "utf-8"));
		response.addCookie(cookie);
		
		// ��ȡ������������Cookie
		Cookie[] cs=request.getCookies();
		// ��ȡ����Ϊname��Cookie
		// cs[] cookie1: age=18   cookie2 name=admin
		Cookie findC=null;
		if(cs!=null) {
			for(Cookie c:cs) {
				if(c.getName().equals("name")) {
					findC=c;
				}
			}
		}
		response.setContentType("text/html;charset=utf-8");
		
		if(findC==null) {
			// ���û��name��Cookie����ʾ�û����ǵ�һ�η���
			response.getWriter().write("first visit");
		}else {
			response.getWriter().write("welcome "+URLDecoder.decode(findC.getValue(), "utf-8"));
		}
	}
}
