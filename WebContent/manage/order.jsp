<%@page import="com.cn.easybuy.entity.*"%>
<%@page import="com.cn.easybuy.dao.impl.*"%>
<%@page import="com.cn.easybuy.dao.*"%>
<%@page import="com.cn.easybuy.util.Pages"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../scripts/function.js"></script>
<script type="text/javascript">
	function check() {
		var obj = document.getElementById("OrderId").innerHTML;
		$.ajax({
			type: "POST",
			url: "updateOrderStuts?OrderId="+obj,
			data: "status="+$("#status").val(),
		});
		location.reload();
	}
</script>
</head>
<body>
 <div id="header" class="wrap">
	<div id="logo"><img src="../images/logo.png" /></div>
	<div class="help"><a href="../index.jsp">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="index.jsp">首页</a></li>
			<li><a href="user.jsp">用户</a></li>
			<li><a href="product.jsp">商品</a></li>
			<li class="current"><a href="order.jsp">订单</a></li>
			<li><a href="guestbook.jsp">留言</a></li>
			<li><a href="news.jsp">新闻</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<jsp:include page="date.jsp" />
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>用户管理</dt>
				<dd><a href="user.jsp">用户管理</a></dd>
			  <dt>商品信息</dt>
				<dd><em><a href="productClass-add.jsp">新增</a></em><a href="productClass.jsp">分类管理</a></dd>
				<dd><em><a href="product-add.jsp">新增</a></em><a href="product.jsp">商品管理</a></dd>
				<dt>订单管理</dt>
				<dd><a href="order.jsp">订单管理</a></dd>
				<dt>留言管理</dt>
				<dd><a href="guestbook.jsp">留言管理</a></dd>
				<dt>新闻管理</dt>
				<dd><em><a href="news-add.jsp">新增</a></em><a href="news.jsp">新闻管理</a></dd>
			</dl>
		</div>
	</div>
	<div class="main">
		<h2>订单管理</h2>
		<div class="manage">
			<div class="search">				
			</div>
			<div class="spacer"></div>
            <form id="orderForm" method="post"  action="order.jsp?or=1">
                 订单号：<input type="text" class="text" name="entityId" id="entityId" />
                 订货人：<input type="text" class="text" name="userName" />
                 <label class="ui-blue"><input type="submit" name="submit" value="查询" /></label>
           </form>
			<table class="list">
				<%
					OrderDao oDao = new OrderDaoImpl();
					ProductDao pDao = new ProductDaoImpl();
					UserDao uDao = new UserDaoImpl();
					Pages p = new Pages();
					String current = request.getParameter("current");
					String error = request.getParameter("error");
					String entityId = (String)request.getSession().getAttribute("entityId");
					String userName = (String)request.getSession().getAttribute("userName");
					String or = request.getParameter("or");
					if(or!=null){
						entityId = request.getParameter("entityId");
						userName = request.getParameter("userName");
						request.getSession().setAttribute("entityId", entityId);
						request.getSession().setAttribute("userName", userName);
					}
					int pc = 1;
					if(current!=null && !current.equals("")){
						pc = Integer.valueOf(current);
					}
					p.setPageSize(1);
					p.setPageIndex(pc);
					List<Order> orders = oDao.getOrderLimit(p, entityId, userName);
					if(orders.size()==0){
					%>
					<tr>
						<th><h1 style="color: red">查询无结果!请重新查询</h1></th>
					</tr>
				<%
				}
					for(Order order : orders){
						User user = uDao.queryUserByID(order.getUserID());
				%>
				<tr>
					<th>订货人:<%=user.getUserName() %><br>订单号：<span id="OrderId"><%=order.getId() %></span> </th>
					<th>时间：<%=order.getCreateTime() %></th>					
					<th colspan="2">
					状态:<%=order.getStatus()<5?order.getStatus()<4?order.getStatus()<3?order.getStatus()<2?"待审核":"审核通过":"配货":"发货":"收货确认" %>
					<select name="status" id="status" onblur="check()">	
					<%
					switch(order.getStatus()){
						case 1: %><option value="1" selected = "selected">待审核</option><option value="2" >审核通过</option><option value="3" >配货</option><option value="4" >发货</option><option value="5" >收货确认</option><%
							break;
						case 2:%><option value="1" >待审核</option><option value="2" selected = "selected">审核通过</option><option value="3" >配货</option><option value="4" >发货</option><option value="5" >收货确认</option><%
							break;
						case 3:%><option value="1" >待审核</option><option value="2" >审核通过</option><option value="3" selected = "selected">配货</option><option value="4" >发货</option><option value="5" >收货确认</option><%
							break;
						case 4:%><option value="1" >待审核</option><option value="2" >审核通过</option><option value="3" >配货</option><option value="4" selected = "selected">发货</option><option value="5" >收货确认</option><%
							break;
						case 5:%><option value="1" >待审核</option><option value="2" >审核通过</option><option value="3" >配货</option><option value="4" >发货</option><option value="5" selected = "selected">收货确认</option><%
							break;
						default:%><option value="1" selected = "selected">待审核</option><option value="2" >审核通过</option><option value="3" >配货</option><option value="4" >发货</option><option value="5" >收货确认</option><%
					}%>					    
					</select></th>
				</tr>
				<%
					int i = 0;
					int j = order.getOraderDetail().size();
					for(OrderDetail detail : order.getOraderDetail()){
						Product product = pDao.getProductByID(detail.getProductID());
						i++;
				%>
				<tr>
					<td class="first w4 c"><img width="50%" src="../<%=product.getFileName() %>" /><br><%=product.getName() %></td>
					<td class="w1 c">单价:<%=product.getPrice() %></td>
					<td class="w1 c">数量:<%=detail.getQuantity() %></td>
					<%
						if(i==1){
					%>
					<td class="w1 c" rowspan="<%=j%>">总计：<%=order.getCost() %></td>
					<%}%>	
				</tr>
				<%} }%>
			</table>
			<div class="pager">
				<ul class="clearfix">
				<li>[当前:<%=p.getPageIndex() %>页/共 <%=p.getPageTotal() %>页]</li>
				<%
				     if(p.getPageIndex()>1){
				%>
				<li><a href="order.jsp?current=1">首页</a></li>
				<li><a href="order.jsp?current=<%=p.getPageIndex()-1 %>">上一页</a></li>
				<%} 
				if(p.getPageIndex()<p.getPageTotal()){ %>
				<li><a href="order.jsp?current=<%=p.getPageIndex()+1 %>">下一页</a></li>
				<li><a href="order.jsp?current=<%=p.getPageTotal() %>">尾页</a></li>
				<% }%>
				</ul>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
