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
		//1. ����-post
		request.setCharacterEncoding("utf-8");
		//2. �����������
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		//3. ����֤
		// �û����ǿ���֤
		if(username==null || "".equals(username)) {
			request.setAttribute("msg", "�û�������Ϊ��");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//4. ִ���߼�
		UserDao dao=new UserDao();
		boolean flag=dao.getUserByUAP(username, password);

		//5. ���ؽ��
		if(flag) {
			request.setAttribute("message", "��¼�ɹ���");
			request.getRequestDispatcher("/WEB-INF/jsp/msg.jsp").forward(request, response);
		}else {
			// ʧ��
			request.setAttribute("msg", "������û���������");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}			
		
	}

}
