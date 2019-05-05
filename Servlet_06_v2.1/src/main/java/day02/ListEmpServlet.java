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
 * 利用JDBC访问数据库，读取员工信息列表
 */
public class ListEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			//访问数据库
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
			//输出网页头部
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>"); 
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>员工列表</h1>");
			//输出表头
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>ID</td>");
			out.println("<td>NAME</td>");
			out.println("</tr>"); 
			
			while(rs.next()) {
				out.println("<tr>");
				
				out.print("<td>");
				out.print(rs.getInt("EMPNO")); //员工号
				out.println("</td>");

				out.print("<td>");
				out.print(rs.getString("ENAME"));//员工名
				out.println("</td>");
				
				out.println("</tr>");
			}
			//输出表格结束
			out.println("</table>");
			//输出网页的结束
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


