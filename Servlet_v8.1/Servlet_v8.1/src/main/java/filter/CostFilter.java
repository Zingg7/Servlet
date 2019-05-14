package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CostFilter implements Filter {
	
	static {
		System.out.println("Filter������");
	}

    public CostFilter() {
    	System.out.println("Filter��ʵ����");
    }

    public void destroy() {
	}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	// ����ͨ��ʱ���м�ʱ
    	long st=System.currentTimeMillis();
    	
    	chain.doFilter(request, response);
    	
    	// ��Ӧͨ��ʱ���м�ʱ
    	long et=System.currentTimeMillis();
    	
    	// �����ʱ
    	System.out.println("cost="+(et-st)+"ms");
	}



	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Filter����ʼ��");
	}

}
