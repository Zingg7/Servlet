package web;

import java.io.IOException;
import javax.servlet.ServletException;
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
			request.setAttribute("message", "登录成功！");
			request.getRequestDispatcher("/WEB-INF/jsp/msg.jsp").forward(request, response);
		}else {
			// 失败
			request.setAttribute("msg", "错误的用户名或密码");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}			
		
	}

}
