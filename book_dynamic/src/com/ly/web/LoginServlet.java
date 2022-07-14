//package com.ly.web;
//
//import com.ly.entity.User;
//import com.ly.service.UserService;
//import com.ly.service.impl.UserServiceImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class LoginServlet extends HttpServlet {
//
//    UserService userService = new UserServiceImpl();
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("utf-8");//设置请求的编码方式，必须在读取请求参数或者输入之前，否则无效；
//        resp.setContentType("text/html");//设置响应的内容类型，这个一定要设置否则，页面中文现实乱码！！！
//        resp.setCharacterEncoding("utf-8");//设置响应的编码方式，告诉浏览器我发送的内容编码类型为utf-8;
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//
//        User loginUser = userService.login(new User(null,username,password,null));
//
//        if (loginUser == null){
//            req.setAttribute("msg","用户名或密码错误！");
//            req.setAttribute("username",username);
//
//            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
//        }else{
//            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
//        }
//    }
//}
