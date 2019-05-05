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
 * 利用EmpDao显示员工列表
 */
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		EmpDao dao = new EmpDao();
		List<Emp> list=dao.findAll();
		
		//输出页面 ？ 
		response.setContentType(
				"text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		//输出网页头
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>员工列表</h1>");
		//输出表格头
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>编号</td>");
		out.println("<td>姓名</td>");
		out.println("<td>工作</td>");
		out.println("<td>管理者</td>");
		out.println("<td>薪水</td>");
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
		
		//输出表格和网页的结尾
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}

