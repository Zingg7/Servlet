package web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 响应乱码
		response.setContentType("text/html;charset=utf-8");
		
		// 获取用户本次访问时间
		long time=System.currentTimeMillis();
		
		// 将本次访问时间封装到Cookie中
		Cookie cookie=new Cookie("time",time+"");
		
		// 设置Cookie存活时间
		cookie.setMaxAge(60*60*24);//24小时
		
		// 设置Cookie的路径
		cookie.setPath(request.getContextPath());
		
		// 将cookie添加到response中
		response.addCookie(cookie);
		
		Cookie c2=new Cookie("name", "张飞");
		response.addCookie(c2);
		
		
		
		// 获取用户本次请求发来的Cookie
		Cookie[] cs=request.getCookies();
		
		// 查找"time"的Cookie
		Cookie findC=null;
		if(cs!=null) {
			for(Cookie c:cs) {
				if("time".equals(c.getName())) {
					findC=c;
				}
			}
		}
		// 根据查询结果返回响应内容
		if(findC==null) {
			response.getWriter().write("您是第一次访问");
		}else {
			response.getWriter().write("您上一次访问时间是："+new Date(Long.parseLong(findC.getValue())));
		}
		
	}

}
