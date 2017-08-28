<%@page import="com.cn.easybuy.util.Pages"%>
<%@page import="com.cn.easybuy.entity.News"%>
<%@page import="java.util.List"%>
<%@page import="com.cn.easybuy.dao.impl.NewsDaoImpl"%>
<%@page import="com.cn.easybuy.dao.NewsDao"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    request.setCharacterEncoding("utf-8");
    
    int pageIndex=1;
   String pageNo= request.getParameter("pageIndex");
   if(pageNo==null)    //第一次访问index。jsp
   {
	   pageIndex=1;
   }else{
	   pageIndex=Integer.valueOf(pageNo);   //点击了下一页
   }
   
   //创建pages类的对象
   Pages pages=new Pages();
   pages.setPageIndex(pageIndex);
   pages.setPageSize(10);
   
   //调用业务方法 ，实现得到总记录数
   
   NewsDao news =new NewsDaoImpl();
   int pageCount=news.getCount();
   pages.setPageCount(pageCount);
   
   //得到相应页面数据
   NewsDao newsdao=new NewsDaoImpl();
   List<News> list1= newsdao.getList(pageIndex, pages.getPageSize());
   session.setAttribute("pages1", pages);
   session.setAttribute("list1",list1);
   response.sendRedirect("news.jsp");
    %>