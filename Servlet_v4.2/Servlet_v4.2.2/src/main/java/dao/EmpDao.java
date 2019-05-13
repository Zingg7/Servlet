package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Emp;

public class EmpDao {
	
	public Integer findMaxEmpno() {
		String sql = "select max(empno) as empno "
				+ "from emp";
		Connection conn=null;
		try {
			conn = DBUtil.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			Integer empno=0;
			while(rs.next()) {
				empno=rs.getInt("empno");
			}
			return empno;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * 查询员工表中全部的工作
	 * @return 工作列表
	 */
	public List<String> findJobs(){
		String sql = "select distinct job from emp";
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			List<String> list = new ArrayList<String>();
			while(rs.next()) {
				list.add(rs.getString("job"));
			}
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * 将emp对象中的数据保存到数据库中
	 * @param emp 代表新添加的数据
	 */
	public void saveEmp(Emp emp) {
		String sql = "insert into emp "
				+ "(empno, ename, job, mgr, "
				+ "hiredate, sal, comm, deptno) "
				+ "values (?,?,?,?,?,?,?,?)"; 
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, emp.getEmpno());
			ps.setString(2, emp.getEname());
			ps.setString(3, emp.getJob());
			ps.setInt(4, emp.getMgr());
			ps.setDate(5, emp.getHiredate());
			ps.setDouble(6, emp.getSal());
			ps.setDouble(7, emp.getComm());
			ps.setInt(8, emp.getDeptno());
			int n = ps.executeUpdate();
			if(n!=1) {
				throw new RuntimeException("插入失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtil.close(conn);
		}
	}
	

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






