package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 封装数据库的连接
 */
public class DBUtil {
	private static BasicDataSource ds;
	//利用properties文件初始化数据库连接池
	static {
		try {
			Properties cfg=new Properties();
			InputStream in = DBUtil.class.getClassLoader()
				.getResourceAsStream("jdbc.properties");
			cfg.load(in);
			String driver=cfg.getProperty("driver");
			String url=cfg.getProperty("url");
			String user=cfg.getProperty("username");
			String pwd=cfg.getProperty("password");
			ds=new BasicDataSource();
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(pwd);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/** 连接到数据库 */
	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/** 关闭数据库连接 */
	public static void close(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}




