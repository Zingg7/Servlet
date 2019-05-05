package day01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * �̳�HttpServlet,��ʵ��Servlet�ӿڼ�
 * ���GenericServlet,��������get,post����
 * ��дdoGet���Ǵ���get����
 * 	 get����: �������ַ��ֱ������ʱget����
 *           a��ǩ��������ʱget����
 *  	     img��ǩ�е�src��get����
 * ��дdoPost���Ǵ���post����
 *   post����: ��method=postʱ�������
 */
public class TestServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("HI HttpServlet doGet()");
	}
}
