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
		// 1. ��������-����-post
		request.setCharacterEncoding("utf-8");
		
		// 2. �����������
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		// 3. ������֤
		// �û����ǿ���֤
		if(username==null || "".equals(username)) {
			// �û���Ϊ��
			request.setAttribute("msg", "�û�������Ϊ��");
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp")
				.forward(request, response);
			return;
		}
		
		UserDao dao=new UserDao();
		// �û������ظ���֤
		boolean hasUsername=dao.getUserByUsername(username);
		if(hasUsername) {
			request.setAttribute("msg", "�û����Ѵ���");
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp")
				.forward(request, response);
			return;
		}
		
		// 4. ִ���߼�
		User user=new User(-1, username, password);
		boolean flag=dao.saveUser(user);
		
		// 5. ������Ӧ����
		String message=flag?"ע��ɹ�":"ע��ʧ��";
		
		request.setAttribute("message", message);
		
		request.getRequestDispatcher("/WEB-INF/jsp/msg.jsp")
			.forward(request, response);

	}
}
