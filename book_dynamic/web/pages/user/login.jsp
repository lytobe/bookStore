<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="utf-8"  %>
<html>
<head>

    <title>尚硅谷会员登录页面</title>
    <%@include file="/pages/common/head.jsp"%>
<%--    <script type="text/javascript">--%>
<%--        $(function () {--%>
<%--            $("#sub_btn").click(function () {--%>
<%--                //验证账号--%>
<%--                var username = $("form input:first").val();--%>

<%--                var usernamepatt = /^\w{5,12}$/;--%>
<%--                if (!usernamepatt.test(username)) {--%>
<%--                    $("span.errorMsg").text("用户名错误")--%>
<%--                    return false;--%>
<%--                }--%>
<%--                //验证密码--%>
<%--				var password = $("input:eq(1)").val();--%>
<%--                var passwordpatt = /^\w{5,12}$/;--%>
<%--                if (!passwordpatt.test(password)) {--%>
<%--                    $("span.errorMsg").text("密码错误")--%>
<%--                    return false;--%>
<%--                }--%>


<%--                $("span.errorMsg").text();--%>
<%--            });--%>
<%--        });--%>
<%--    </script>--%>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>书城会员</h1>
                    <a href="pages/user/regist.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
<%--                        <%=request.getAttribute("msg")==null?"请输入用户名或密码":request.getAttribute("msg")%>--%>
                        ${empty requestScope.msg?"请输入用户名和密码":requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="login"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off"
                               tabindex="1" name="username" value="${requestScope.username}"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off"
                               tabindex="1" name="password"/>
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                        <%
                            String username = request.getParameter("username");
                            application.setAttribute("username",username);
                        %>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>