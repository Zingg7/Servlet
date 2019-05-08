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
		// 实现记住用户名功能
		
		// 获取用户请求发来的用户名
		String username=request.getParameter("name");
		// 将用户名通过Cookie发送给用户
		// response.addHeader("set-cookie", "name="+username);
		Cookie cookie=new Cookie("name", URLEncoder.encode(username, "utf-8"));
		response.addCookie(cookie);
		
		// 获取本次请求发来的Cookie
		Cookie[] cs=request.getCookies();
		// 获取名称为name的Cookie
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
			// 如果没有name的Cookie，提示用户您是第一次访问
			response.getWriter().write("first visit");
		}else {
			response.getWriter().write("welcome "+URLDecoder.decode(findC.getValue(), "utf-8"));
		}
	}
}
