<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet与JSP转发协作</title>
</head>
<body>
	<h1>Servlet与JSP转发协作</h1>
	<p>
		将JSP作为Servlet的显示组件,显示Servlet中处理的结果
	</p>
	<%
		String msg = (String)request.getAttribute("msg");
	%>
	显示Servlet传递到jsp的数据:<%=msg%>
 	<%-- <% 
 		String str = (String)request.getAttribute("msg");
		request.removeAttribute("msg");
 	%>
 	<%=str%> --%>
</body>
</html>






