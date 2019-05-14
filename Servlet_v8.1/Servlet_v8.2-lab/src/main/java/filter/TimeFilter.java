package filter;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TimeFilter implements Filter {

	int floor;
	int ceil;
	
	public void init(FilterConfig fConfig) throws ServletException {
		floor=Integer.parseInt(fConfig.getInitParameter("floor"));
		ceil=Integer.parseInt(fConfig.getInitParameter("ceil"));
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 实现图片的限时访问
		
		//1. 获取当前时间的分钟分量
		// 获取当前时间
		Calendar c=Calendar.getInstance();
		// 获取当前的分钟分量
		int minute=c.get(Calendar.MINUTE);

		
		//2. 判断分钟分量是否在可访问的区间内
		if(minute<floor || minute >ceil) {
			//3.1 如果不在，返回错误提示信息
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("当前不是访问时间");
		}else {
			//3.2 如果在，放行
			chain.doFilter(request, response);
		}
	}
	
	
	public void destroy() {
	}


}
