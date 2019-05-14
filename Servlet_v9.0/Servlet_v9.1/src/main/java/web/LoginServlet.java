package web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 乱码-post
		request.setCharacterEncoding("utf-8");
		//2. 接收请求参数
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String remname=request.getParameter("remname");
		
		
		//3. 表单验证
		// 用户名非空验证
		if(username==null || "".equals(username)) {
			request.setAttribute("msg", "用户名不能为空");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//4. 执行逻辑
		UserDao dao=new UserDao();
		boolean flag=dao.getUserByUAP(username, password);

		//5. 返回结果
		if(flag) {
			// 向Session中添加用户登录状态
			request.getSession().setAttribute("user", username);

			// 实现记住用户名功能
			// 判断用户是否勾选了记住用户名 NPE
			if("true".equals(remname)) {
				// 创建Cookie保存用户的用户名
				Cookie cookie=new Cookie("remname",URLEncoder.encode(username, "utf-8"));
				// 设定存活时间
				cookie.setMaxAge(60*60*24*7);// 7天
				// 设定路径
				cookie.setPath(request.getContextPath());
				
				response.addCookie(cookie);
			}else {
				// 删除之前发送的remname的Cookie
				Cookie cookie=new Cookie("remname","");
				cookie.setMaxAge(0);
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
			}
			request.setAttribute("message", "登录成功！");
			request.getRequestDispatcher("/WEB-INF/jsp/msg.jsp").forward(request, response);
		}else {
			// 失败
			request.setAttribute("msg", "错误的用户名或密码");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}			
		
	}

}
