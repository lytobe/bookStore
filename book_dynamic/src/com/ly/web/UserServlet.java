package com.ly.web;

import com.ly.entity.User;
import com.ly.service.UserService;
import com.ly.service.impl.UserServiceImpl;
import com.ly.utils.WebBeanUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();//销毁session
        resp.sendRedirect(req.getContextPath());
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");//设置请求的编码方式，必须在读取请求参数或者输入之前，否则无效；
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = WebBeanUtil.copyParameterToBean(req.getParameterMap(),new User());

        User loginUser = userService.login(new User(null, username, password, null));

        if (loginUser == null) {
            req.setAttribute("msg", "用户名或密码错误！");
            req.setAttribute("username", username);

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user",loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);//获取session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);//删除验证码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        // 2、检查 验证码是否正确 === 写死,要求验证码为:abcde
//        User user = new User();
//        WebBeanUtil.copyParameterToBean(req.getParameterMap(),user);
        //优化后（工具类使用泛型）
        User user = WebBeanUtil.copyParameterToBean(req.getParameterMap(),new User());

//        try {
//            BeanUtils.populate(user,req.getParameterMap());
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

        if (token.equalsIgnoreCase(code)) {
            // 3、检查 用户名是否可用
            if (userService.exitsUsername(username)) {
                System.out.println("用户名[" + username + "]已存在!");
                // 跳回注册页面
                req.setAttribute("msg", "用户名已存在");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                // 可用
                // 调用 Service 保存到数据库
                userService.register(new User(null, username, password, email));
                // 跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

}
