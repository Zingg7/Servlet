package day03;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

/**
 * ��ʾServlet��JSPЭ��ת��
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) 
			throws ServletException, IOException {
		//�����ݴ洢��request��������JSP��������
		req.setAttribute("msg", "Hello World!");
		
		//��JSP��ServletЭ������ʱ����ӦӦ����JSP���� Servelt�в�Ҫ������Ӧ
		//resp.getWriter().write("Hello"); 
		//resp.flushBuffer();
		
		//RequestDispatcher ����Э������
		//��������ʱ����Ҫ�ṩһ��Ŀ��ҳ���·��
		RequestDispatcher rd=req.getRequestDispatcher(
				"/WEB-INF/jsp/message.jsp");
		//����forward����ת����Ŀ��JSP����ִ��
		//����Ŀ��JSP�뵱ǰServlet������ͬһ��req, resp
		rd.forward(req, resp);
	}
}





