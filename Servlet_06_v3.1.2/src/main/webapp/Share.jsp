<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>案例</title>
</head>
<body>
	<h1>request对象</h1>
	<p>
		request对象共享数据
	</p>
	<%
		//将信息存储到 request 的缓存中
		request.setAttribute("msg", "Hello World!"); 
	%>
	取出request中的数据
 	<% 
 		String str = (String)request.getAttribute("msg");
		request.removeAttribute("msg");
 	%>
 	<%=str%>
</body>
</html>






