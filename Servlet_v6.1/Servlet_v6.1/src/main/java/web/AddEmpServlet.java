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
 * 动态展示添加Emp的表单
 */
public class AddEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		EmpDao dao = new EmpDao();
		//找到全部的员工信息，作为回报人列表数据
		List<Emp> managers = dao.findAll();
		request.setAttribute("managers", managers);
		
		//找到全部工作信息作为工作列表
		List<String> jobs=dao.findJobs();
		request.setAttribute("jobs", jobs);
		request.getRequestDispatcher(
				"/WEB-INF/jsp/add-emp.jsp")
				.forward(request, response);
	}

}





