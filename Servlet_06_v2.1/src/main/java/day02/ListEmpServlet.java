package day02;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ����JDBC�������ݿ⣬��ȡԱ����Ϣ�б�
 */
public class ListEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			//�������ݿ�
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/newdb3";
			String username="root";
			String password="";
			conn = DriverManager.getConnection(
					url, username, password);
			String sql = "select * from emp";
			Statement st = conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			//�����ҳͷ��
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>"); 
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Ա���б�</h1>");
			//�����ͷ
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>ID</td>");
			out.println("<td>NAME</td>");
			out.println("</tr>"); 
			
			while(rs.next()) {
				out.println("<tr>");
				
				out.print("<td>");
				out.print(rs.getInt("EMPNO")); //Ա����
				out.println("</td>");

				out.print("<td>");
				out.print(rs.getString("ENAME"));//Ա����
				out.println("</td>");
				
				out.println("</tr>");
			}
			//���������
			out.println("</table>");
			//�����ҳ�Ľ���
			out.println("</body>");
			out.println("</html>");
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null) {
					conn.close();
				} 
			} catch (Exception e2) {
				e2.printStackTrace();
			} 
		}
		
	}

}


