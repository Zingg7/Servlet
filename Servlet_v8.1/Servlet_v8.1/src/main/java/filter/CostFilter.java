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
		System.out.println("Filter被加载");
	}

    public CostFilter() {
    	System.out.println("Filter被实例化");
    }

    public void destroy() {
	}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	// 请求通过时进行计时
    	long st=System.currentTimeMillis();
    	
    	chain.doFilter(request, response);
    	
    	// 响应通过时进行计时
    	long et=System.currentTimeMillis();
    	
    	// 输出耗时
    	System.out.println("cost="+(et-st)+"ms");
	}



	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Filter被初始化");
	}

}
