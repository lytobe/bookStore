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
//        req.setCharacterEncoding("utf-8");//��������ı��뷽ʽ�������ڶ�ȡ���������������֮ǰ��������Ч��
//        resp.setContentType("text/html");//������Ӧ���������ͣ����һ��Ҫ���÷���ҳ��������ʵ���룡����
//        resp.setCharacterEncoding("utf-8");//������Ӧ�ı��뷽ʽ������������ҷ��͵����ݱ�������Ϊutf-8;
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//
//        User loginUser = userService.login(new User(null,username,password,null));
//
//        if (loginUser == null){
//            req.setAttribute("msg","�û������������");
//            req.setAttribute("username",username);
//
//            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
//        }else{
//            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
//        }
//    }
//}
