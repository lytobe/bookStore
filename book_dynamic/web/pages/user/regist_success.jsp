<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="utf-8"  %>
<!DOCTYPE html>
<html>
<head>

    <title>会员注册成功页面</title>
   <%@include file="/pages/common/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word"></span>
    <%--静态包含登陆成功后的菜单--%>
    <%@include file="/pages/common/login_success_menu.jsp"%>
</div>

<div id="main">

    <h1>注册成功! <a href="index.jsp">转到主页</a></h1>

</div>

<%@include file="/pages/common/footer.jsp"%>
</body>
</html>