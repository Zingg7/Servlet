package web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��Ӧ����
		response.setContentType("text/html;charset=utf-8");
		
		// ��ȡ�û����η���ʱ��
		long time=System.currentTimeMillis();
		
		// �����η���ʱ���װ��Cookie��
		Cookie cookie=new Cookie("time",time+"");
		
		// ����Cookie���ʱ��
		cookie.setMaxAge(60*60*24);//24Сʱ
		
		// ����Cookie��·��
		cookie.setPath(request.getContextPath());
		
		// ��cookie��ӵ�response��
		response.addCookie(cookie);
		
		Cookie c2=new Cookie("name", "�ŷ�");
		response.addCookie(c2);
		
		
		
		// ��ȡ�û�������������Cookie
		Cookie[] cs=request.getCookies();
		
		// ����"time"��Cookie
		Cookie findC=null;
		if(cs!=null) {
			for(Cookie c:cs) {
				if("time".equals(c.getName())) {
					findC=c;
				}
			}
		}
		// ���ݲ�ѯ���������Ӧ����
		if(findC==null) {
			response.getWriter().write("���ǵ�һ�η���");
		}else {
			response.getWriter().write("����һ�η���ʱ���ǣ�"+new Date(Long.parseLong(findC.getValue())));
		}
		
	}

}
