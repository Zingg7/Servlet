package day02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ����get�������
 * ���������get�����͵�tomcat�ķ�����ʱ��,tomcat���������ͷ��,
 * ������ͷ�����ݴ洢��Request�����д��ݵ�servlet,
 * ��servlet�п�������getParamter������ȡ�������
 */
public class TestServlet extends HttpServlet {
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
		doGet(request, response);
	}

}
