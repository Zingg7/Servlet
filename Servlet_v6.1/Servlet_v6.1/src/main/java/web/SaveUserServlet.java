package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;

public class SaveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 处理乱码-请求-post
		request.setCharacterEncoding("utf-8");
		
		// 2. 接收请求参数
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		// 3. 参数验证
		// 用户名非空验证
		if(username==null || "".equals(username)) {
			// 用户名为空
			request.setAttribute("msg", "用户名不能为空");
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp")
				.forward(request, response);
			return;
		}
		
		UserDao dao=new UserDao();
		// 用户名不重复验证
		boolean hasUsername=dao.getUserByUsername(username);
		if(hasUsername) {
			request.setAttribute("msg", "用户名已存在");
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp")
				.forward(request, response);
			return;
		}
		
		// 4. 执行逻辑
		User user=new User(-1, username, password);
		boolean flag=dao.saveUser(user);
		
		// 5. 返回响应内容
		String message=flag?"注册成功":"注册失败";
		
		request.setAttribute("message", message);
		
		request.getRequestDispatcher("/WEB-INF/jsp/msg.jsp")
			.forward(request, response);

	}
}
