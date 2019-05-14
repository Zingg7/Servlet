<!-- TOC -->
- [今天的任务：去掉jsp页面中所有的Java代码](#今天的任务去掉jsp页面中所有的java代码)
    - [JSP的标签技术](#jsp的标签技术)
    - [JSP的九大内置对象](#jsp的九大内置对象)
<!-- /TOC -->


## JSP的标签技术
1. 背景
	1. 开发JSP技术的目的，是解决Servlet非常不适合输出html内容的问题
	2. 原来是Java代码嵌入html的内容，JSP是html的内容嵌入java代码
	3. Sun公司在jsp2.0中推出了jsp标签技术，可以以标签的方式来执行逻辑，动态输出内容，取代JSP中所有的Java代码。Sun公司建议，以后在JSP中，不要再出现任何的java代码

2. EL表达式 （表达式语言）
	1. 使用 ${ 表达式 }的表达式，来替代<%=java表达式 %> 
	2. 常见功能：
		1. 在页面中动态输出运算结果
		2. 动态输出变量的值 ${变量名}
			1. 默认情况下，可以动态输出作用域中的数据
			2. 如果不指定作用域，el会从最小的作用域开始查找对应的变量，如找到，则输出，如找不到，则继续向上找；如果都没找到，则什么都不输出
			3. 可以手动指定仅从某个作用域中查找变量，找到就输出，找不到就什么都不输出
				
				${requestScope.city}
				${sessionScope.city}
				${applicationScope.city}
			4.    username=<%=request.getParameter("username") %>
			可以${param.username} 限定从请求参数中获取username的值

		3. 输出数组中的数据
			1. ${arr[0]}
			2. 不会出现下标越界异常，什么都不显示
		4. 输出集合中的数据
			1. ${list[0]}
			2. 不会出现下标越界异常，什么都不显示
		5. 输出Map集合中的数据
			1. ${map.key}
			2. ${map["key"]}
			3. 注意，第二种中的双引号不能省略
		6. 输出Java对象中的数据
			1. ${person.name}
			2. el表达式会将person.name自动翻译成person.getName()
			3. 必须保证该类有对应的get方法，不然会抛出异常

3. JSTL(JSP Standard Tag Library)标签库
	1. sun公司推出了自定义标签技术
	2. 推出一套标准标签库，贡献给Apache，JSTL
	3. sun公司在后续的jsp升级中，号召开发者都使用jstl标签
	4. 使用JSTL的步骤
		1. 开发者使用JSTL标签库，首先必须导入jstl的jar包
		2. 在jsp页面上申明所使用的标签库
	
			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			1. uri=指定使用的是哪一个子库
			2. core：核心标签库，是开发中最常用的子库
			3. fmt：和格式化相关的库，可以按照对应格式显示数字，字符，时间等
				1. 1288.8	 -》  ￥ 1,288.80元
			4. functions：可以和el表达式配合使用的一些标签，用的非常少
			5. sql：JSP访问数据库使用的标签，现在不用了
			6. xml：JSP解析XML文件所使用的标签，现在也不用了

4. 常用的core库的子标签
	1. c:if 做判断
		1. <c:if test="" var="" scope="">
			1. test="${布尔表达式}"，如果值为true,<c:if></c:if>中间的内容会被执行
			2. var="变量名"
			3. scope="page|request|session|application"，如果不加scope属性，默认存入page中
			4. c:if标签会自动将 test的值，以var指定的变量名，存入scope指定的作用域中
	2. c:choose 模拟switch case
	3. c:forEach 执行循环，既可以做普通for，也可以做增强for
		1. 实现普通for
			1. begin:指定循环开始的位置
			2. end:指定循环结束的位置
			3. step:步长
			4. var:循环变量
		2. 实现增强for
			1. items:指定遍历哪个数组/集合
			2. var：循环变量
			3. varStatus：是封装了当前循环状态的对象
				1. count:当前循环的次数，从1开始
				2. first: boolean值，代表当前是否是循环的第一次
				3. last: boolean值，代表当前是否是循环的最后一次


## JSP的九大内置对象
1. JSP的九大内置对象，其实是翻译后的.java文件中 _jspService()方法的局部变量
2. 具体的九大内置对象
	1. request
	2. response
	3. session
	4. application:ServletContext
	5. out:JspWriter,类似于response.getWriter(),输出jsp中页面的内容
	6. pageContext
	7. config:ServletConfig
	8. page:this,当前Servlet实例
	9. exception:Throwable


3. pageContext : page上下文
	1. 代表当前jsp的运行环境
	2. 当请求到达当前jsp时，创建一个pageContext对象，当请求离开当前jsp时，销毁该对象
	3. pageContext是JavaEE的四大作用域(Scope)之一，是最小的作用域，代表当前页面
		
		pageContext.setAttribute();
		pageContext.getAttribute();
		pageContext.removeAttribute();
![](day10-3.png)

以下是JavaEE的作用域的是：答案（124）
1. request  2.session  3.page  4.pageContext


4. 可以开发一个错误提示页面，当项目出现500时，服务器自动使用该错误提示页面为用户生成响应内容
	1. 好处：可以有效提高用户体验
	2. 配置方式

			<error-page>
				<error-code>500</error-code>
				<location>/500Page.jsp</location>
			</error-page>
	3. 如果在错误提示页面中，希望获得上一个页面的异常信息，可以在页面中声明 isErrorPage="true"
	4. 服务器为会该JSP自动添加一个内置对象，exception，封装了上一个页面的异常信息
