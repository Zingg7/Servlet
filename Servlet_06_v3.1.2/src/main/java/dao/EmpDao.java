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