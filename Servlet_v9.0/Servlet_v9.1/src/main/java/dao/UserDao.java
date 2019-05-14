package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.tedu.entity.User;

public class UserDao {
	
	/**
	 * �����û�������������ݵķ���
	 * @param username
	 * @param password
	 * @return true-������ false-û������
	 */
	public boolean getUserByUAP(String username,String password) {
		String sql="select * from user where username=? and password=?";
		
		try(Connection conn=DBUtil.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * ��ѯ�û����Ƿ���ڵķ���
	 * @param username
	 * @return true-�û������� false-������
	 */
	public boolean getUserByUsername(String username) {
		String sql="select * from user where username=?";
		try(Connection conn=DBUtil.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, username);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	public boolean saveUser(User user) {
		String sql="insert into user values(null,?,?)";
		try(Connection conn=DBUtil.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql)
				){
			// �󶨲���
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			// ִ�в������
			int n=ps.executeUpdate();
			if(n==1) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
