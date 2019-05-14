package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest)request;
		// 判断用户的Session中是否有登录状态
		HttpSession session=req.getSession(false);
		if(session==null || session.getAttribute("user")==null) {
			// 无则将请求重定向到登录页面
			((HttpServletResponse)response).sendRedirect(req.getContextPath()+"/login.jsp");
		}else {
			// 有则放行
			chain.doFilter(request, response);
		}
	}
  
    public LoginFilter() {
    }

	public void destroy() {
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
