<%--
  Created by IntelliJ IDEA.
  User: luoya
  Date: 2022/7/4
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="utf-8"  %>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";

    request.setAttribute("bathPath",basePath);
%>
<%--<%=basePath%>--%>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/script/jquery-1.7.2.min.js"></script>