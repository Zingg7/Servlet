package day03;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpDao;
import entity.Emp;

/**
 * ����Servlet��JSPЭ����ʾԱ���б�
 */
public class ListEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		EmpDao dao = new EmpDao();
		List<Emp> list = dao.findAll();
		
		//����request������JSP���ͼ�����
		request.setAttribute("list", list); 
		
		//ת����JSPҳ����ʾ��� list-emp.jsp
		RequestDispatcher rd=request.getRequestDispatcher(
				"/WEB-INF/jsp/list.jsp");
		rd.forward(request, response); 
	}

}





