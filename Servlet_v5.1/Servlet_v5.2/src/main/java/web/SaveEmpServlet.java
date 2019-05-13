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
 * ��������Ա����Ϣ�ı��������ݣ����ұ��浽���ݿ���
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
			
			//����������¹��������µĹ������ӵ�Ա����Ϣ
			String addJob=request.getParameter("addJob");
			if(addJob!=null && !addJob.isEmpty()) {
				job = addJob;
			}
			
			EmpDao dao = new EmpDao();
			//findMaxEmpno()���ҵ�ǰ����Ա����
			//�Զ�����Ա���ţ���Ա����ΪԱ�������ֵ��1
			int empno = dao.findMaxEmpno()+1;
			
			Emp emp = new Emp(empno, ename, job, mgr,
					hiredate, sal, comm, deptno);
			
			dao.saveEmp(emp);
			//�ɹ�
			//request.setAttribute("message", "����ɹ���");
			
			response.sendRedirect("listEmp"); 
			return;
		}catch(ParseException e) {
			e.printStackTrace();
			//...
		}catch (Exception e) {
			e.printStackTrace();
			//ʧ��
			request.setAttribute("message", "����ʧ�ܣ�");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/msg.jsp")
		.forward(request, response);
	}

}





