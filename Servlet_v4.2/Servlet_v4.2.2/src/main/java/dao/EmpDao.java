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
	 * ��ѯԱ������ȫ���Ĺ���
	 * @return �����б�
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
	 * ��emp�����е����ݱ��浽���ݿ���
	 * @param emp ��������ӵ�����
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
				throw new RuntimeException("����ʧ�ܣ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtil.close(conn);
		}
	}
	

	/**
	 * ��װ���ݿ��������
	 * �����ݿ��ѯ������浽List���� 
	 */
	public List<Emp> findAll(){
		Connection conn = null;
		try {
			//1. ���ӵ����ݿ�
			conn = DBUtil.getConnection();
			//2. ִ��SQL
			String sql = "select * from emp";
			Statement st=conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			//3. ��rs�е����ݴ洢�� List
			//   ���rs��û�����ݣ��򷵻ؿռ���
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






