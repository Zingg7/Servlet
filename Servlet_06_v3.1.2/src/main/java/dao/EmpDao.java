package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Emp;

public class EmpDao {

	/**
	 * 封装数据库操作代码
	 * 将数据库查询结果缓存到List返回 
	 */
	public List<Emp> findAll(){
		Connection conn = null;
		try {
			//1. 连接到数据库
			conn = DBUtil.getConnection();
			//2. 执行SQL
			String sql = "select * from emp";
			Statement st=conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			//3. 将rs中的数据存储到 List
			//   如果rs中没有数据，则返回空集合
			List<Emp> list=new ArrayList<Emp>();
			while(rs.next()) {
				int empno=rs.getInt("empno");
				String ename=rs.getString("ename");
				String job=rs.getString("job");
				int mgr = rs.getInt("mgr");
				Date hiredate=rs.getDate("hiredate");
				double sal = rs.getDouble("sal");
				double comm = rs.getDouble("comm");
				int deptno=rs.getInt("deptno");
				Emp emp=new Emp(empno, ename, job, 
					mgr, hiredate, sal, comm, deptno);
				list.add(emp);
 			}
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtil.close(conn); 
		}
	}
}