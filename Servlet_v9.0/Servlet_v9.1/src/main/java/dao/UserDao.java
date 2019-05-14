package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.tedu.entity.User;

public class UserDao {
	
	/**
	 * 根据用户名和密码查数据的方法
	 * @param username
	 * @param password
	 * @return true-有数据 false-没有数据
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
	 * 查询用户名是否存在的方法
	 * @param username
	 * @return true-用户名存在 false-不存在
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
			// 绑定参数
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			// 执行插入操作
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
