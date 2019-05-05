<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Java脚本</title>
</head>
<body>
	<h1>JSP中的Java脚本</h1>
	<h2>Java脚本</h2>
	<%
		System.out.println("试试Java程序！"); 
		for(int i=0; i<5; i++){
			//网页中输出信息 0,1,2,3,4,
			out.print(i + ","); 
		}
	%>	
	<h2>JSP表达式</h2>
	<%="Time:"%> <%=System.currentTimeMillis()%>
	<h2>JSP声明</h2>
	<%! //当前Servlet属性和方法
		int a = 5;
		public double test(){
			return Math.PI*a*a;
		}
	%>
	利用表达式调用test方法
	<%=test()%>
</body>
</html>






