- [Servlet/JSP](#Servlet/JSP)
    - [Tomcat Web 服务器](#tomcat-web-服务器)
        - [使用Tomcat](#使用tomcat)
        - [Serlvet Hello World](#serlvet-hello-world)
            - [案例1 使用Servlet接口创建Servlet](#案例1-使用servlet接口创建servlet)
    - [HttpServlet](#httpservlet)
        - [使用GenericServlet创建Servlet](#使用genericservlet创建servlet)
            - [案例2 相对于实现Servlet接口 继承Generic是更简单的方式](#案例2-相对于实现Servlet接口-继承Generic是更简单的方式)
        - [使用 HttpServlet创建Servlet](#使用-httpservlet创建servlet)
            - [案例3 继承 HttpServlet比实现Servlet接口简单](#案例3-继承HttpServlet比实现Servlet接口简单)
            - [案例4 利用网页处理get请求](#案例4-利用网页处理get请求)
            - [案例5 利用HttpServlet处理post请求](#案例5-利用httpservlet处理post请求)
            - [案例6 既能处理get也能处理post请求的Servlet](#案例6-既能处理get也能处理post请求的servlet)
    - [HttpServletRequest](#httpservletrequest)<BR>
            - [案例7 利用Request获取请求信息](#案例7-利用request获取请求信息)
    - [HttpServletResponse](#httpservletresponse)<BR>
            - [案例8 利于Response对象向客户端发送信息](#案例8-利于response对象向客户端发送信息)
	
    - [处理请求参数](#处理请求参数)<BR>
            - [案例: 获取post请求](#案例-获取post请求)<BR>
            - [案例: 获取get请求](#案例-获取get请求)<BR>
    - [处理多选(数组)参数 getParameterValues](#处理多选数组参数-getparametervalues)<BR>
            - [案例](#案例)
    - [在Servlet中使用JDBC](#在servlet中使用jdbc)<BR>
            - [案例](#案例-1)
    - [利用DAO封装数据库访问](#利用dao封装数据库访问)


# Servlet/JSP

## Tomcat Web 服务器

Tomcat 是一个现成Web服务器，其将HTTP通信功能都实现了，使用Tomcat只需要编写简单的Servlet就可以处理HTTP编程。

### 使用Tomcat 

1. 下载 Tomcat apache-tomcat-7.0.93.zip
	- http://tomcat.apache.org
2. 释放到硬盘上
	1. Windows释放到 D:/apache-tomcat-7.0.93
	2. Linux 释放到 /home/soft01/apache-tomcat-7.0.93
3. 在Eclipse中配置使用Tomcat
4. 启动Tomcat查看 http://localhost:8080

### Serlvet Hello World

Servlet是Oralce(SUN)定义的开发规范：

1. 固定的目录结构
	
		webapp
		  |-- WEB-INF
		  |   |-- web.xml  (部署描述文件)配置请求与Serlvet的映射关系 
		  |   |              /hello -> day01.HelloServlet
		  |   |-- lib      放置第三方的库 如：数据库驱动程序等
		  |   |-- classes  放置自己写的，编译后的类
		  |   |    |-- day01.HelloServlet.class
		  |-- index.html
		  |-- logo.png

2. 固定的接口名
	1. Servlet 接口
	2. HelloServlet 类必须实现Servlet接口（还可以继承HttpServlet）

3. 固定的配置文件规则 web.xml 
	
		<servlet>
			<servlet-name>hello</servlet-name>
			<servlet-class>day01.HelloServlet</servlet-class>
		</servlet>
		<servlet-mapping>
			<servlet-name>hello</servlet-name>
			<url-pattern>/hello</url-pattern>
		</servlet-mapping>  
	url请求hello时,去找与其name对应的接口的类名(class)

4. 将应用程序部署（复制）到Tomcat中
- Server -> Add and Remove 


**使用Maven项目创建Web项目步骤：**
	在JAVA EE视图中
1. 创建Maven项目，选择**war**方式 // 自动创建目录结构
2. 利用右键菜单生成部署描述文件(Deplyment Description - Generate Deployment Descriptor Stub)
3. 导入 Targeted Runtimes // 导入接口(Tomcat-Servlet)
4. 创建Servlet类
5. 部署测试


#### 案例1 使用Servlet接口创建Servlet

1. 创建类

		public class HelloServlet implements Servlet{
			// Servlet内的方法
			public void init(ServletConfig cfg)	throws ServletException {
			}
			public void destroy() {
			}
			public ServletConfig getServletConfig() {
				return null;
			}
			public String getServletInfo() {
				return null;
			}
			// request代表用户发的请求信息,把发给用户的信息写到response
			public void service(ServletRequest req, ServletResponse res)throws ServletException, IOException {
				// 用户接受的是一个网页
				res.setContentType("text/html");
				PrintWriter out=res.getWriter();
				out.println("<html><body><h1>Hello World</h1></body></html>");
			}
		}

2. 配置web.xml
		
		  <servlet>
		    <servlet-name>hello</servlet-name>
		    <servlet-class>day01.HelloServlet</servlet-class>
		  </servlet>
		  <servlet-mapping>
		    <servlet-name>hello</servlet-name>
		    <url-pattern>/hello</url-pattern>
		  </servlet-mapping>

3. 部署测试：
	1. 部署
		Add and Remove
	2. 测试
		http://localhost:8080/Servlet01/hello


## HttpServlet

实现Servlet接口(init etc)：

1. 直接Servlet接口编程繁琐(完全的空接口)
2. Servlet 接口有两个实现类
	1. GenericServlet: 实现了全部方法
	2. HttpServlet: 实现了全部方法,分清了get/post请求
3. 实现 HttpServlet 更加简便
4. HttpServlet区分了Http请求类型
	1. get 请求被doGet方法处理
	2. post 请求被doPost处理
	3. doGet方法中调用一下doPost，就可以一起处理get和Post


### 使用GenericServlet创建Servlet 

#### 案例2 相对于实现Servlet接口 继承Generic是更简单的方式

1. 编写类

		public class DemoServlet extends GenericServlet{
			public void service(ServletRequest req, ServletResponse res)throws ServletException, IOException {
				res.setContentType("text/html");
				PrintWriter out = res.getWriter();
				out.print("HI GenericServlet"); 
			}
		}

2. 配置 web.xml

		  <servlet>
		    <servlet-name>demo1</servlet-name>
		    <servlet-class>day01.DemoServlet</servlet-class>
		  </servlet>
		  <servlet-mapping>
		    <servlet-name>demo1</servlet-name>
		    <url-pattern>/demo1</url-pattern>
		  </servlet-mapping>

3. 部署测试


### 使用 HttpServlet创建Servlet
	
#### 案例3 继承HttpServlet比实现Servlet接口简单
	相比 GenericServlet 可以区别 get、post请求  
	重写 doGet 就是处理 get 请求
	 get请求： 浏览器地址栏直接请求是get请求
	          a标签连接请求是get请求 // 点击超链接,请求跳转
	          img标签中的src是get请求 
	重写 doPost 就是处理 post 请求
	 post请求： 表单method=post时候的请求

1. 创建类

		public class TestServlet extends HttpServlet {
			
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				resp.setContentType("text/html");
				PrintWriter out=resp.getWriter();
				out.println("HI HttpServlet doGet()"); 
			}
		}

2. 配置 web.xml
		
		  <servlet>
		    <servlet-name>demo2</servlet-name>
		    <servlet-class>day01.TestServlet</servlet-class>
		  </servlet>
		  <servlet-mapping>
		    <servlet-name>demo2</servlet-name>
		    <url-pattern>/demo2</url-pattern>
		  </servlet-mapping>

3. 测试

#### 案例4 利用网页处理get请求

1. 编写网页 webapp/demo.html

		<!DOCTYPE html>
		<html>
		<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		</head>
		<body>
			<h1>测试get请求</h1>
			<a href="demo2">test</a>

			<h1>测试post请求</h1>
			<p>客户端向服务器发起post请求，但是服务器只能处理get请求，此时服务器端会出现 405错误！</p>
			<form action="demo2" method="post">
				<input type="submit" value="GO">  
			</form>		
		</body>
		</html>

2. 测试

#### 案例5 利用HttpServlet处理post请求

1. 编写Servlet类

		/**
		 * 处理Post请求，就需要重写doPost方法
		 */
		public class DoPostServlet extends HttpServlet{
			
			@Override
			protected void doPost(HttpServletRequest req, 
					HttpServletResponse resp) 
				throws ServletException, IOException {
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.println("Hi doPost"); 
			}
		
		}

2. 配置

		  <servlet>
		    <servlet-name>demo3</servlet-name>
		    <servlet-class>day01.DoPostServlet</servlet-class>
		  </servlet>
		  <servlet-mapping>
		    <servlet-name>demo3</servlet-name>
		    <url-pattern>/demo3</url-pattern>
		  </servlet-mapping>

3. 编写网页
	
		<p>正确处理post请求案例</p>
		<form action="demo3" method="post">
			<input type="submit" value="GO">  
		</form>
	
4. 测试

#### 案例6 既能处理get也能处理post请求的Servlet

1. 编写Servlet

		/**
		 * 既能处理get请求也能处理post请求 
		 * 1. get请求-> doGet() -> doPost()
		 * 2. post请求 -> doPost() 
		 */
		public class GetPostServlet extends HttpServlet{
			
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				doPost(req, resp); 
			}
			
			@Override
			protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				resp.setContentType("text/html");
				PrintWriter out=resp.getWriter();
				out.print("get & post"); 
			}
		}

2. 配置

		  <servlet>
		    <servlet-name>demo4</servlet-name>
		    <servlet-class>day01.GetPostServlet</servlet-class>
		  </servlet>
		  <servlet-mapping>
		    <servlet-name>demo4</servlet-name>
		    <url-pattern>/demo4</url-pattern>
		  </servlet-mapping>

3. 编写 html

		<p>即能处理get也能处理post请求</p>
		<a href="demo4">test</a>
		<form action="demo4" method="post">
			<input type="submit" value="GO">  
		</form>

4. 测试


## HttpServletRequest

客户端浏览器发起的请求消息，经过Tomcat解析以后，封装到Request对象中。

利用Request提供API可以获取请求中的信息。

#### 案例7 利用Request获取请求信息
	 演示 Request 对象的功能, Request 代表用户浏览器发送的请求信息

1. 编写Servlet

		public class RequestDemoServlet extends HttpServlet {
			private static final long serialVersionUID = 1L;
		
			protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//读取请求行上的 Method 信息
				String method=request.getMethod();
				//读取请求行上的 Request-URI
				String uri = request.getRequestURI();
				//读取请求行上的协议版本
				String protocol = request.getProtocol();
				
				//读取请求头信息 
				// User-Agent 用户代理，就是浏览器的信息, 类型版本等 
				// 获取请求头中的用户浏览器相关信息
				String ua=request.getHeader("User-Agent");
				String host = request.getHeader("Host");
				
				//设置服务器发送端的编码规则
				//response.setCharacterEncoding("UTF-8");
				//设置浏览器接收时候的解码规则
				response.setContentType("text/html; charset=UTF-8");
				
				//设置 contentType 时候，response会自动设置CharacterEncoding
				
				PrintWriter out = response.getWriter();
				out.print("<html>");
				out.print("<body>");
				out.print("<ul>");
				out.print("<li>"+method+"</li>");
				out.print("<li>"+uri+"</li>");
				out.print("<li>"+protocol+"</li>");
				out.print("<li>"+ua+"</li>");
				out.print("<li>"+host+"</li>");
				out.print("<li>试试</li>");
				out.print("</ul>");
				out.print("</body>");
				out.print("</html>");
			}
		}

2. 配置

		  <servlet>
		    <description></description>
		    <display-name>RequestDemoServlet</display-name>
		    <servlet-name>RequestDemoServlet</servlet-name>
		    <servlet-class>day01.RequestDemoServlet</servlet-class>
		  </servlet>
		  <servlet-mapping>
		    <servlet-name>RequestDemoServlet</servlet-name>
		    <url-pattern>/reqdemo</url-pattern>
		  </servlet-mapping>

3. 测试

## HttpServletResponse

	Response对象代表服务器向客户端发送的信息

#### 案例8 利于Response对象向客户端发送信息

1. Servlet类
		
		public class ResponseDemoServlet extends HttpServlet {
			private static final long serialVersionUID = 1L;
		       
			protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//设置响应行状态码
				response.setStatus(404); 
				//添加一个自定义的响应消息头：
				response.addHeader("Demo", "Hello World!"); 
				response.setContentType("text/html; charset=UTF-8");
				//一定在设置编码以后，获取out对象！！否则有编码错误
				PrintWriter out = response.getWriter();
				//设置响应的实体Body
				out.println("<html>");
				out.println("<body>");
				out.println("<h1>404，没有找到妹子呀！</h1>");
				out.println("</body>");
				out.println("</html>");
			}
		
		}
		
2. 配置

		  <servlet>
		    <description></description>
		    <display-name>ResponseDemoServlet</display-name>
		    <servlet-name>ResponseDemoServlet</servlet-name>
		    <servlet-class>day01.ResponseDemoServlet</servlet-class>
		  </servlet>
		  <servlet-mapping>
		    <servlet-name>ResponseDemoServlet</servlet-name>
		    <url-pattern>/respdemo</url-pattern>
		  </servlet-mapping>

3. 测试


## 处理请求参数

HttpServletRequest对象提供了获取get、post请求参数的方法 **getParamter**

1. Tomcat收到浏览器请求时候
	1. 先解析请求行和请求头，然后将这些信息存储到request对象中
	2. 执行Servlet可以收到request
2. 对于Post请求
	1. post请求参数在"请求Body"中传输到服务器
	2. request对象在第一次调用getParamter时候解析请求Body中的参数
	3. request会按照ISO8859-1编码解析post请求参数
3. 对于get请求
	1. get请求参数在"请求行"中传递到服务器
	2. tomcat默认按照ISO8859-1编码解析请求行和请求头，并且存储到request对象
	3. getParamter方法执行时候不进行编码解析

#### 案例: 获取post请求

1. 编写客户端页面 login.html

		<!DOCTYPE html>
		<html>
		<head>
		<meta charset="UTF-8">
		<title>登录页</title>
		</head>
		<body>
			<h1>登录</h1>
			<p>利用Servlet处理请求参数, 表单大多使用post请求</p>
			<form action="login" method="post">
				<div>
					<label>用户</label>
					<input type="text" name="username">
				</div>
				<div>
					<label>密码</label>
					<input type="password" name="pwd">
				</div>
				<div>
					<input type="submit" value="登录"> 
				</div>
			</form>
		</body> 
		</html>
		
2. 编写Servlet处理请求

		/**
		 * 在Servlet中接收表单参数
		 */
		public class LoginServlet extends HttpServlet {
			private static final long serialVersionUID = 1L;
		
			protected void doPost(HttpServletRequest request, 
					HttpServletResponse response) 
					throws ServletException, IOException {
				/*
				 * 用户浏览器发起的 Post 请求被Tomcat接收，
				 * Tomcat将请求信息解析到 Request 对象中。
				 * 任何请求信息，都可以从Request对象中获取
				 * 如获取请求参数：request.getParamter("username")
				 *  paramter: 参数
				 */
				//setCharacterEncoding通知Request对象
				//在解析 请求Body 时候使用UTF-8编码
				//必须在 getParameter 方法调用之前设置
				request.setCharacterEncoding("UTF-8");
				
				String name=request.getParameter("username");
				System.out.println("name="+name); 
				String pwd=request.getParameter("pwd");
				System.out.println("pwd="+pwd);  
					
				response.setContentType(
						"text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<html><body>");
				//简易密码验证逻辑
				if(name.equals("tom") && pwd.equals("123")) {
					out.println("<h1>登录成功</h1>");
				}else {
					out.println("<h1>登录失败</h1>");
				}
				out.println("</body></html>");
			}
		
		}


3. 测试

#### 案例: 获取get请求

1. 编写Servlet
		
		/**
		 * 处理get请求参数
		 * 当浏览器的get请求发送到Tomcat服务器时候，Tomcat会
		 * 解析请求头部，将请求头部数据存储到Request对象中
		 * 传递到 Servlet。在Servlet中可以利用getParamter方法
		 * 获取get请求参数
		 */
		public class TestServlet extends HttpServlet {
			private static final long serialVersionUID = 1L;
		
			protected void doGet(HttpServletRequest request,
					HttpServletResponse response) 
					throws ServletException, IOException {
				//获取get请求参数时候，如果key对应的参数不存在
				//就返回null
				//request.setCharacterEncoding("UTF-8");
				String name = request.getParameter("name");
				String age = request.getParameter("age");
				String pwd = request.getParameter("pwd");
				System.out.println("name="+name);
				System.out.println("age="+age);
				System.out.println("pwd="+pwd);
				
				response.setContentType(
						"text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html></body>");
				out.println("<h1>收到get参数！</h1>");
				out.println("</body></html>");
			}
		
		}

2. 测试：

		http://localhost:8080/Servlet02/test?name=范&age=18


** post请求中文编码解析 **

1. request对象会在第一次调用getParamter方法时候解析post请求参数。
2. 在第一次调用getParamter之前设置解析编码就可以解决post请求编码问题。
	- request.setCharacterEncoding("UTF-8");

** get请求中文编码解析 **

1. Tomcat会在创建request对象之前解析get请求参数，所以设置request的编码对get请求无效。
2. 修改Tomcat的配置参数，可以解决处理get请求中文编码解析问题。
	1. `<Connector URIEncoding="UTF-8" ... `

配置Tomcat7： 

    <Connector URIEncoding="UTF-8"
     connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443"/>

## 处理多选(数组)参数 getParameterValues

#### 案例

1. 编写 多选表单：reg.html

		<!DOCTYPE html>
		<html>
		<head>
		<meta charset="UTF-8">
		<title>reg.html</title>
		</head>
		<body>
			<h1>注册</h1>
			<p>多选参数传输（数组）</p>
			<form action="reg" method="post">
				<div>
					<label>爱好：</label>
				</div>
				<div>
					<label for="h1">读书</label>
					<input id="h1" type="checkbox" name="hobby"
					 value="读书">
					<label for="h2">运动</label>
					<input id="h2" type="checkbox" name="hobby"
					 value="运动">
					<label for="h3">电子竞技</label>
					<input id="h3" type="checkbox" name="hobby"
					 value="电子竞技">
				</div>
				<div>
					<input type="submit" value="保存">  
				</div>
			</form>
		</body>
		</html>

2. 编写Servlet
		
		public class RegServlet extends HttpServlet {
			private static final long serialVersionUID = 1L;
		
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				/*
				 * 在Servlet中获取多选（数组）参数 
				 */
				request.setCharacterEncoding("UTF-8");
				String[] hobby=request.getParameterValues("hobby");
				System.out.println("hobby="+Arrays.toString(hobby));
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<html><body>");
				out.println("<h1>收到多选参数</h1>");
				out.println("</body></html>");
			}
		
		}

3. 测试

## 在Servlet中使用JDBC

在Servlet中使用JDBC访问数据库，可以将数据库中的数据显示到网页中

#### 案例

1. 导入JDBC

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.6</version>
		</dependency>
		<!-- 数据库连接池 -->
		<dependency>
			<groupId>commons-dbcp</groupId>
			<artifactId>commons-dbcp</artifactId>
			<version>1.4</version>
		</dependency>
		<!-- junit测试框架 -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.12</version>
		</dependency>

2. 测试JDBC

		/*
		 * 测试是否能够连接到数据库
		 */
		@Test
		public void testJDBC() throws Exception{
			//System.out.println("Hello");
			// 加载注册数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/newdb3";
			String user = "root";
			String password = "";
			Connection conn = DriverManager.getConnection(url, user, password);
			String sql = "select * from emp"; 
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				System.out.print(rs.getInt("EMPNO")+",");
				System.out.println(rs.getString("ENAME"));
			}
			conn.close();
		}
		
3. 编写Servlet访问数据库

		/**
		 * 利用JDBC访问数据库，读取员工信息列表
		 */
		public class ListEmpServlet extends HttpServlet {
			private static final long serialVersionUID = 1L;
		       
			protected void doGet(HttpServletRequest request, 
					HttpServletResponse response) throws ServletException, IOException {
				// 连接数据库 -> 执行SQL -> 拼接网页
				// 数据访问: EmpDao; 数据连接: DBUtil; 拼接网页: Servlet 
				Connection conn = null;
				try {
					//访问数据库
					Class.forName("com.mysql.jdbc.Driver");
					String url="jdbc:mysql://localhost:3306/newdb3";
					String username="root";
					String password="";
					conn = DriverManager.getConnection(
							url, username, password);
					String sql = "select * from emp";
					Statement st = conn.createStatement();
					ResultSet rs=st.executeQuery(sql);
					
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out=response.getWriter();
					//输出网页头部
					out.println("<!DOCTYPE html>");
					out.println("<html>");
					out.println("<head>");
					out.println("<meta charset='UTF-8'>"); 
					out.println("</head>");
					out.println("<body>");
					out.println("<h1>员工列表</h1>");
					//输出表头
					out.println("<table>");
					out.println("<tr>");
					out.println("<td>ID</td>");
					out.println("<td>NAME</td>");
					out.println("</tr>"); 
					
					while(rs.next()) {
						out.println("<tr>");
						
						out.print("<td>");
						out.print(rs.getInt("EMPNO")); //员工号
						out.println("</td>");
						
						out.print("<td>");
						out.print(rs.getString("ENAME"));//员工名
						out.println("</td>");
						
						out.println("</tr>");
					}
					//输出表格结束
					out.println("</table>");
					//输出网页的结束
					out.println("</body>");
					out.println("</html>");
					conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if (conn != null) {
							conn.close();
						} 
					} catch (Exception e2) {
						e2.printStackTrace();
					} 
				}
				
			}
		
		}

4. 配置web.xml

		<servlet>
			<description></description>
			<display-name>ListEmpServlet</display-name>
			<servlet-name>ListEmpServlet</servlet-name>
			<servlet-class>day02.ListEmpServlet</servlet-class>
		</servlet>
		<servlet-mapping>
			<servlet-name>ListEmpServlet</servlet-name>
			<url-pattern>/list</url-pattern>
		</servlet-mapping>

5. 测试

		http://localhost:8080/Servlet02/list

## 利用DAO封装数据库访问

如上案例中的ListEmpServlet代码问题

1. 繁琐冗长，不便于维护
2. 数据库访问代码和网页代码混合，不便于维护
3. 更新页面显示繁琐，复杂度非常高。

解决方案：

1. 拆分数据访问与页面显示代码，将数据访问代码封装到DAO中。
2. 将页面显示功能利用JSP实现（下节课内容）

#### 案例

1. 封装数据库连接到DBUtil类

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

2. 创建数据库配置文件 jdbc.properties

		driver=com.mysql.jdbc.Driver
		url=jdbc:mysql://localhost:3306/newdb3?seUnicode=true&characterEncoding=UTF-8
		username=root
		password=

3. 创建实体类 Emp

		/**
		 * 员工实体类
		 * 实体类：一般与数据库中的表对应的类
		 * 	EMPNO
			ENAME
			JOB
			MGR
			HIREdate
			SAL
			COMM
			DEPTNO
		 *
		 */
		public class Emp {
			private Integer empno;
			private String ename;
			private String job;
			private Integer mgr;
			private Date hiredate; //java.sql.Date
			private Double sal;
			private Double comm;
			private Integer deptno;
			
			public Emp() {
			}
		
			public Emp(Integer empno, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm,
					Integer deptno) {
				super();
				this.empno = empno;
				this.ename = ename;
				this.job = job;
				this.mgr = mgr;
				this.hiredate = hiredate;
				this.sal = sal;
				this.comm = comm;
				this.deptno = deptno;
			}
		
			public Integer getEmpno() {
				return empno;
			}
		
			public void setEmpno(Integer empno) {
				this.empno = empno;
			}
		
			public String getEname() {
				return ename;
			}
		
			public void setEname(String ename) {
				this.ename = ename;
			}
		
			public String getJob() {
				return job;
			}
		
			public void setJob(String job) {
				this.job = job;
			}
		
			public Integer getMgr() {
				return mgr;
			}
		
			public void setMgr(Integer mgr) {
				this.mgr = mgr;
			}
		
			public Date getHiredate() {
				return hiredate;
			}
		
			public void setHiredate(Date hiredate) {
				this.hiredate = hiredate;
			}
		
			public Double getSal() {
				return sal;
			}
		
			public void setSal(Double sal) {
				this.sal = sal;
			}
		
			public Double getComm() {
				return comm;
			}
		
			public void setComm(Double comm) {
				this.comm = comm;
			}
		
			public Integer getDeptno() {
				return deptno;
			}
		
			public void setDeptno(Integer deptno) {
				this.deptno = deptno;
			}
		
			@Override
			public String toString() {
				return "Emp [empno=" + empno + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", hiredate=" + hiredate
						+ ", sal=" + sal + ", comm=" + comm + ", deptno=" + deptno + "]";
			}
			
		}


4. 创建EmpDao封装数据库访问功能

		public class EmpDao {
		
			/**
			 * 封装数据库操作代码
			 * 将数据库查询结果缓存到List返回 
			 */
			public List<Emp> findAll(){
				Connection conn = null;
				try {
					//1. 连接到数据库
					conn = DBUtil.getConnection();
					//2. 执行SQL
					String sql = "select * from emp";
					Statement st=conn.createStatement();
					ResultSet rs = st.executeQuery(sql);
					//3. 将rs中的数据存储到 List
					//   如果rs中没有数据，则返回空集合
					List<Emp> list=new ArrayList<Emp>();
					while(rs.next()) {
						int empno=rs.getInt("empno");
						String ename=rs.getString("ename");
						String job=rs.getString("job");
						int mgr = rs.getInt("mgr");
						Date hiredate=rs.getDate("hiredate");
						double sal = rs.getDouble("sal");
						double comm = rs.getDouble("comm");
						int deptno=rs.getInt("deptno");
						Emp emp=new Emp(empno, ename, job, 
							mgr, hiredate, sal, comm, deptno);
						list.add(emp);
		 			}
					return list;
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}finally {
					DBUtil.close(conn); 
				}
			}
		}

5. 测试：

		@Test
		public void testFindAll() {
			EmpDao dao = new EmpDao();
			List<Emp> list=dao.findAll();
			for (Emp emp : list) {
				System.out.println(emp); 
			}
		}

6. 创建ListServlet调用EmpDao访问数据库

		/**
		 * 利用EmpDao显示员工列表
		 */
		public class ListServlet extends HttpServlet {
			private static final long serialVersionUID = 1L;
		       
			protected void doGet(HttpServletRequest request, 
					HttpServletResponse response) throws ServletException, IOException {
				
				EmpDao dao = new EmpDao();
				List<Emp> list=dao.findAll();
				
				//输出页面 ？ 
				response.setContentType(
						"text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				//输出网页头
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset='UTF-8'>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>员工列表</h1>");
				//输出表格头
				out.println("<table>");
				out.println("<tr>");
				out.println("<td>编号</td>");
				out.println("<td>姓名</td>");
				out.println("<td>工作</td>");
				out.println("<td>管理者</td>");
				out.println("<td>薪水</td>");
				out.println("</tr>");
				
				for(Emp emp: list) {
					out.println("<tr>");
					out.println("<td>"+emp.getEmpno()+"</td>");
					out.println("<td>"+emp.getEname()+"</td>");
					out.println("<td>"+emp.getJob()+"</td>");
					out.println("<td>"+emp.getMgr()+"</td>");
					out.println("<td>"+emp.getSal()+"</td>");
					out.println("</tr>");
				}
				
				//输出表格和网页的结尾
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
			}
		
		}

> 可以看到经过剥离封装数据库访问后，相对于ListEmpServlet来说已经简化了许多。

7. 配置：

		<servlet>
			<description></description>
			<display-name>ListServlet</display-name>
			<servlet-name>ListServlet</servlet-name>
			<servlet-class>day02.ListServlet</servlet-class>
		</servlet>
		<servlet-mapping>
			<servlet-name>ListServlet</servlet-name>
			<url-pattern>/listall</url-pattern>
		</servlet-mapping>

8. 测试

		http://localhost:8080/Servlet02/listall
		

