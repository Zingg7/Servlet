package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.EmpDao;
import cn.tedu.entity.Emp;

/**
 * ��̬չʾ���Emp�ı�
 */
public class AddEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		EmpDao dao = new EmpDao();
		//�ҵ�ȫ����Ա����Ϣ����Ϊ�ر����б�����
		List<Emp> managers = dao.findAll();
		request.setAttribute("managers", managers);
		
		//�ҵ�ȫ��������Ϣ��Ϊ�����б�
		List<String> jobs=dao.findJobs();
		request.setAttribute("jobs", jobs);
		request.getRequestDispatcher(
				"/WEB-INF/jsp/add-emp.jsp")
				.forward(request, response);
	}

}





