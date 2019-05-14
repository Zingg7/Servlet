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
		//1. ����-post
		request.setCharacterEncoding("utf-8");
		//2. �����������
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String remname=request.getParameter("remname");
		
		
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
			// ��Session������û���¼״̬
			request.getSession().setAttribute("user", username);

			// ʵ�ּ�ס�û�������
			// �ж��û��Ƿ�ѡ�˼�ס�û��� NPE
			if("true".equals(remname)) {
				// ����Cookie�����û����û���
				Cookie cookie=new Cookie("remname",URLEncoder.encode(username, "utf-8"));
				// �趨���ʱ��
				cookie.setMaxAge(60*60*24*7);// 7��
				// �趨·��
				cookie.setPath(request.getContextPath());
				
				response.addCookie(cookie);
			}else {
				// ɾ��֮ǰ���͵�remname��Cookie
				Cookie cookie=new Cookie("remname","");
				cookie.setMaxAge(0);
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
			}
			request.setAttribute("message", "��¼�ɹ���");
			request.getRequestDispatcher("/WEB-INF/jsp/msg.jsp").forward(request, response);
		}else {
			// ʧ��
			request.setAttribute("msg", "������û���������");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}			
		
	}

}
