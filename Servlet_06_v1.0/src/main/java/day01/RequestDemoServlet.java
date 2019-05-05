package day01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestDemoServlet
 * 
 * ��ʾRequest����Ĺ���
 * Request�����û���������͵�������Ϣ
 */
public class RequestDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestDemoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ�������ϵ�Method��Ϣ
		String method = request.getMethod();
		// ��ȡ�������ϵ�Request-URI
		String uri = request.getRequestURI();
		// ��ȡ�������ϵ�Э��汾
		String protocol = request.getProtocol();
		
		// ��ȡ����ͷ��Ϣ
		// user-Agent �û�����,�������������Ϣ,���Ͱ汾��
		// ��ȡ����ͷ�е��û�����������Ϣ
		String ua= request.getHeader("User-Agent");
		String host = request.getHeader("Host");
		
		// ���÷��������Ͷ˵ı������
		response.setCharacterEncoding("utf-8");
		// �������������ʱ��Ľ������
		response.setContentType("text/html; charset=utf-8");
		
		// ����ContentType��ʱ��,response���Զ�����CharacterEncoding 
		 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<body>");
		out.print("<ul>");
		out.print("<li>"+method+"</li>");
		out.print("<li>"+uri+"</li>");
		out.print("<li>"+protocol+"</li>");
		out.print("<li>"+ua+"</li>");
		out.print("</ul>");
		out.print("</body>");
		out.print("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
