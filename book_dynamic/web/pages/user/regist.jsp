<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="utf-8"  %>
<!DOCTYPE html>
<html>
<head>

    <title>会员注册页面</title>
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        //页面加载完成之后
        $(function () {
            //给验证码的图片绑定单击事件
            $("#code_img").click(function (){
               this.src="${basePath}kaptcha.jpg?d="+new Date();
            });

            //给注册按钮绑定单击事件
            $("#sub_btn").click(function () {
                //验证用户名
                //1获取输入框的内容
                var username = $("#username").val();
                //2创建正则表达式对象
                var usernamepatt = /^\w{5,12}$/;
                //3使用test方法
                if (!usernamepatt.test(username)) {
                    //4提示用户结果
                    $("span.errorMsg").text("用户名不合法");
                    return false;//停留在注册界面
                }

                //验证密码
                var password = $("#password").val();
                var passwordpatt = /^\w{5,12}$/;
                if (!passwordpatt.test(password)) {
                    $("span.errorMsg").text("密码不合法");
                    return false;
                }

				//确认密码
				var repwd = $("#repwd").val();
				if (repwd !=password){
					$("span.errorMsg").text("两次密码不一致");
					return false;
				}

				//验证邮箱
				var email = $("#email").val();
				var emailpatt = /^\w+([-+.]\w+)*@\w+([-.]\\w+)*\.\w+([-.]\w+)*$/;
				if (!emailpatt.test(email)){
					$("span.errorMsg").text("邮箱格式不正确");
					return false;
				}



				//验证码
				var code = $("#code").val();
				//去掉验证码里的空格
				code = $.trim(code);
				if (code == null || code == ""){
					$("span.errorMsg").text("验证码错误");
					return false;
				}

				$("span.errorMsg").text();
            });

        });
    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册书城会员</h1>
                    <span class="errorMsg">
<%--                        <%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
                        ${requestScope.msg?"":requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="regist"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username" value="${requestScope.username}"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email" value="${requestScope.email}"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 130px;" name="code" id="code"/>
                        <img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width:80px;height:40px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>