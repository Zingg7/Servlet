package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpDao;
import entity.Emp;

/**
 * 接收添加员工信息的表单的数据，并且保存到数据库中
 */
public class SaveEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			//String strEmpno = request.getParameter("empno");
			//int empno = Integer.parseInt(strEmpno);
			
			String ename = request.getParameter("ename");
			String job = request.getParameter("job");
			String strMgr = request.getParameter("mgr");
			int mgr = Integer.parseInt(strMgr);
			String strHiredate = 
					request.getParameter("hiredate");
			SimpleDateFormat fmt=new SimpleDateFormat(
					"yyyy-MM-dd");
			Date date = fmt.parse(strHiredate);
			java.sql.Date hiredate=new java.sql.Date(
					date.getTime());
			String strSal = request.getParameter("sal");
			double sal = Double.parseDouble(strSal);
			String strComm = request.getParameter("comm");
			double comm=Double.parseDouble(strComm);
			String strDeptno=request.getParameter("deptno");
			int deptno=Integer.parseInt(strDeptno);
			
			//如果添加了新工作，将新的工作添加到员工信息
			String addJob=request.getParameter("addJob");
			if(addJob!=null && !addJob.isEmpty()) {
				job = addJob;
			}
			
			EmpDao dao = new EmpDao();
			//findMaxEmpno()查找当前最大的员工号
			//自动生成员工号，新员工号为员工号最大值加1
			int empno = dao.findMaxEmpno()+1;
			
			Emp emp = new Emp(empno, ename, job, mgr,
					hiredate, sal, comm, deptno);
			
			dao.saveEmp(emp);
			//成功
			//request.setAttribute("message", "保存成功！");
			
			//response.sendRedirect("listEmp"); 
			request.getRequestDispatcher("listEmp").forward(request, response);
			return;
		}catch(ParseException e) {
			e.printStackTrace();
			//...
		}catch (Exception e) {
			e.printStackTrace();
			//失败
			request.setAttribute("message", "保存失败！");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/msg.jsp")
		.forward(request, response);
	}

}






