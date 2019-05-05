package day02;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpDao;
import day02.Emp;

/**
 * ����EmpDao��ʾԱ���б�
 */
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		EmpDao dao = new EmpDao();
		List<Emp> list=dao.findAll();
		
		//���ҳ�� �� 
		response.setContentType(
				"text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		//�����ҳͷ
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Ա���б�</h1>");
		//������ͷ
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>���</td>");
		out.println("<td>����</td>");
		out.println("<td>����</td>");
		out.println("<td>������</td>");
		out.println("<td>нˮ</td>");
		out.println("</tr>");
		
		for(Emp emp: list) {
			out.println("<tr>");
			out.println("<td>"+emp.getEmpno()+"</td>");
			out.println("<td>"+emp.getEname()+"</td>");
			out.println("<td>"+emp.getJob()+"</td>");
			out.println("<td>"+emp.getMgr()+"</td>");
			out.println("<td>"+emp.getSal()+"</td>");
			out.println("</tr>");
		}
		
		//���������ҳ�Ľ�β
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}

