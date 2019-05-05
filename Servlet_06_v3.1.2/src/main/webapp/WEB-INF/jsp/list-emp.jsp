<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List,entity.Emp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>显示员工列表</title>
</head>
<body>
	<h1>员工列表</h1>
	<%
		//获取Servlet传递的数据
		List<Emp> list=(List<Emp>)
			request.getAttribute("list");
	%>
	<table>
		<tr>
			<td>编号</td>
			<td>姓名</td>
			<td>工作</td>
			<td>老板</td>
			<td>日期</td>
			<td>薪资</td>
		</tr>
		<% for(Emp emp:list){ %>
		<tr>
			<td><%=emp.getEmpno()%></td>
			<td><%=emp.getEname()%></td>
			<td><%=emp.getJob()%></td>
			<td><%=emp.getMgr()%></td>
			<td><%=emp.getHiredate()%></td>
			<td><%=emp.getSal()%></td>
		</tr>
		<%} %>
	</table>
</body>
</html>















