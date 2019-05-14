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
		// ʵ��ͼƬ����ʱ����
		
		//1. ��ȡ��ǰʱ��ķ��ӷ���
		// ��ȡ��ǰʱ��
		Calendar c=Calendar.getInstance();
		// ��ȡ��ǰ�ķ��ӷ���
		int minute=c.get(Calendar.MINUTE);

		
		//2. �жϷ��ӷ����Ƿ��ڿɷ��ʵ�������
		if(minute<floor || minute >ceil) {
			//3.1 ������ڣ����ش�����ʾ��Ϣ
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("��ǰ���Ƿ���ʱ��");
		}else {
			//3.2 ����ڣ�����
			chain.doFilter(request, response);
		}
	}
	
	
	public void destroy() {
	}


}
