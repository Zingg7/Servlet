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
	<h1>JSP功能演示</h1>
	<p>
		将一个Java字符串数组展示为一个无序列表
	</p>
	<%
		String[] arr={"Java", "PHP", "C++", "Python"};
	%>
	<ul>
	<%for(String s:arr){%>
		<li><%=s %></li>
	<%} %>
	</ul>
	<p>JSP中的代码，目的是为了拼接HTML标签！</p>
</body>
</html>






