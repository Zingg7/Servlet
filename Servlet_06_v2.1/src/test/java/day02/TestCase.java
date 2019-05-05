package day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.junit.Test;

import dao.EmpDao;

public class TestCase {
	/**
	 * 测试是否能连接到数据库
	 */
	@Test
	public void testJDBC() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/newdb3";
		String user = "root";	
		String password = "";
		Connection conn = DriverManager.getConnection(url,user,password);
		String sql = "select * from emp";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			System.out.print(rs.getInt("EMPNO")+",");
			System.out.println(rs.getString("ENAME"));
		}
		conn.close();
	}
	

	@Test
	public void testFindAll() {
		EmpDao dao = new EmpDao();
		List<Emp> list=dao.findAll();
		for (Emp emp : list) {
			System.out.println(emp); 
		}
	}
}
