package day02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��Servlet�н��ձ�����
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡget�������ʱ��,���key��Ӧ�Ĳ���������,�ͷ���null
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String pwd = request.getParameter("pwd");
		System.out.println("name: "+name);
		System.out.println("age: "+age);
		System.out.println("pwd: "+pwd);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.println("<h1>�յ�get����</h1>");
		out.print("</body></html>");
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* �û�����������post����Tomcat����
		 * Tomcat��������Ϣ������Request������
		 * �κ�������Ϣ,�����Դ�Request�����л�ȡ
		 * ���ȡ�������:request.getParameter("username")
		 */
		// setCharacterEncoding֪ͨRequest����
		// �ڽ��� ����Body ʱ��ʹ��UTF-8����
		// ������  getParameter ��������֮ǰ����
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("username");
		System.out.println("name: "+name);
		String pwd = request.getParameter("pwd");
		System.out.println("pwd: "+pwd);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		if (name.equals("tom")&&pwd.equals("123")) {
			out.println("<h1>��¼�ɹ�</h1>");
		} else {
			out.println("<h1>��¼ʧ��</h1>");
		}
		out.print("</body></html>");
	}

}
