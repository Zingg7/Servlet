## Session
是将会话状态保存在服务器的技术

###  Session的原理
1. 服务器为每个用户创建一个Session对象，保存该用户的会话状态
2. 服务器将该Session的id，通过JSESSIONID的Cookie发送给浏览器保存
3. 浏览器在后续的请求中，会自动携带JSESSIONID的Cookie，服务器通过该Cookie的值，找到用户对应的Session对象
![](day08-1.png)

### 为什么用Session？
1. 相对Cookie来说更安全
2. Session仅需要传输JSESSIONID的Cookie，不占用服务器的网络带宽
3. Session中的数据没有大小限制（如果内存空间不足以保存数据，会自动将数据保存在服务器的硬盘上）

### 怎么用？
		// 返回该用户对应的Session对象
		HttpSession session=request.getSession(boolean flag);
		flag:true:  有则使用，无则创建
			 false: 有则使用，无则返回null
		
		HttpSession session=request.getSession();//等同于传入true
		
		session.setAttribute(String name,Object value);
		Object value=session.getAttribute(String name);
		session.removeAttribute(String name);
![](day08-3.png)

### Session发挥作用依赖于Cookie
如果浏览器关闭，JSESSIONID的Cookie会被销毁，用户再次访问服务器时，服务器无法找到用户对应的Session对象


### Session和Request的区别

		request.setAttribute(String name,Object value);
		session.setAttribute(String name,Object value);
1. 可以利用Session在一次请求的内部去共享数据，比如Servlet共享数据给JSP
2. 但是Session存活的时间比Request久很多，存入Session中的数据不能得到及时的释放，会增加服务器内存的负担
3. 因此，仅需要在一次请求内部进行的数据共享，使用Request
4. 需要跨请求（在多次请求之间）实现的数据共享，使用Session

### Session的销毁

1. 超时
	1. 默认情况下，一个Session对象可以存活30分钟（从用户最后一次访问该Session开始算）
	2. 可以手动指定Session的存活时间，在web.xml文件中配置，单位是分钟\

		<session-config>
			<session-timeout>30</session-timeout>
		</session-config>

	3. 超时时间越长，用户体验越好，服务器内存压力越大
	4. 超时时间越短，用户体验越差，服务器内存压力越小

2. 马上销毁

		// 马上销毁该Session
		session.invalidate();
		// 常用于用户注销用例

3. Session的钝化和活化（了解）
	1. 当服务器正常关闭时，未超时的Session会被序列化到服务器的硬盘上保存，这个过程称为钝化
	2. 当服务器再次启动后，钝化的Session会被反序列化到内存中，继续发挥作用，这个过程称为活化


### Cookie和Session的区别？
1. Cookie是将会话状态保存在浏览器的技术
	1. Cookie中保存的数据时间可以比较久（例如30天）
	2. Cookie中数据的安全性和稳定性较差
	3. Cookie中的数据大小有限制，约4kb
2. Session是将会话状态保存在服务器的技术
	1. Session中保存的数据时间比较短（默认30分钟超时）
	2. Session中的数据的安全性和稳定性较高
	3. Session中的数据大小理论上没有限制


## Filter（过滤器）
1. 是Sun公司提供的一种特殊的组件，可以对用户的请求进行拦截，执行特定的功能
2. 常见的应用：过滤敏感词，访问权限控制，压缩响应内容
![](day08-5.png)

### 如何开发一个过滤器
1. 开发一个过滤器类，实现Filter接口
2. 重写Filter接口中定义的3个抽象方法
	1. init()
	2. destory()
	3. doFilter()：添加过滤器生效的逻辑
3. 在web.xml中配置一个Filter
		<filter>
			<filter-name>
			<filter-class>
		</filter>
		<filter-mapping>
			<filter-name>
			<url-pattern>过滤器所拦截的url
		</filter-mapping>

### 一个Filter拦截读个请求
1. 新添加一组<filter-mapping>标签
2. 使用 * 作为通配符
	1. <url-pattern>/*</url-pattern>  拦截所有请求
	2. <url-pattern>/user/*</url-pattern> 拦截所有/user目录下的请求


### 多个过滤器配合使用
1. 多个Filter生效的顺序由 <filter-mapping>标签在web.xml中出现的顺序来决定
2. <filter-mapping>标签越靠前，Filter越优先生效


### Filter的生命周期
1. 分为4个阶段：创建、初始化、服务、销毁

2. 创建：服务器启动，即加载和实例化配置的所有Filter
 
3. 初始化：在实例化之后，马上调用Filter的init()方法，执行初始化的逻辑(由开发者来提供)

4. 服务：每当有请求和该Filter拦截的url一致时，服务器会调用该Filter的doFilter()方法，对请求进行拦截，该方法在整个生命周期中会被调用多次

5. 销毁：当服务器关闭或者当前Web应用被移出容器时，Filter会被销毁，销毁前服务器会调用它的destory()方法，执行销毁的逻辑（由开发者提供）

6. Servlet和Filter在整个项目运行阶段，都只有一个实例，不同请求是基于多线程访问同一个实例
![](day08-8.png)


###  FilterConfig
1. 封装了一个Filter在web.xml中配置的初始化参数的对象
2. 当服务器加载和实例化一个Filter时，会读取web.xml中该Filter的配置
3. 服务器会创建一个FilterConfig对象，封装读取到的配置信息
4. 服务器会在调用一个Filter的init()方法时，将FilterConfig对象传进来
5. 开发者仅需要调用FilterConfig对象的api,就可以直接拿到在web.xml中配置的参数
		<filter>
		    <display-name>EncodingFilter</display-name>
		    <filter-name>EncodingFilter</filter-name>
		    <filter-class>filter.EncodingFilter</filter-class>
		  	<!-- 为当前Filter配置一个初始化参数 -->
		  	<init-param>
		  		<param-name>encode</param-name>
		  		<param-value>utf-8</param-value>
		  	</init-param>
		</filter>

		String value=config.getInitParameter(String name);
![](day08-9.png)

#### 案例：实现图片的限时访问
![](day08-10.png)



#### 案例
1. 基于Session统计用户的访问次数
![](day08-2.png)

2. 统计一次请求的耗时
![](day08-7.png)


