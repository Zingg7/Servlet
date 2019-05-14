package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	String encode = null;

	public void init(FilterConfig fConfig) throws ServletException {
		encode=fConfig.getInitParameter("encode");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 处理请求乱码
		request.setCharacterEncoding(encode);

		// 处理响应乱码
		response.setContentType("text/html;charset="+encode);

		chain.doFilter(request, response);
	}

	public EncodingFilter() {
	}

	public void destroy() {
	}

}
