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
//public class RegistServlet extends HttpServlet {
//    private UserService userService = new UserServiceImpl();
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // 1����ȡ����Ĳ���
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
//        String code = req.getParameter("code");
//        // 2����� ��֤���Ƿ���ȷ === д��,Ҫ����֤��Ϊ:abcde
//        if ("abcde".equalsIgnoreCase(code)) {
//            // 3����� �û����Ƿ����
//            if (userService.exitsUsername(username)) {
//                System.out.println("�û���[" + username + "]�Ѵ���!");
//                // ����ע��ҳ��
//                req.setAttribute("msg","�û����Ѵ���");
//                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
//            } else {
//                // ����
//                // ���� Service ���浽���ݿ�
//                userService.register(new User(null, username, password, email));
//                // ����ע��ɹ�ҳ�� regist_success.jsp
//                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
//            }
//        } else {
//            req.setAttribute("msg","��֤�����");
//            req.setAttribute("username",username);
//            req.setAttribute("email",email);
//            System.out.println("��֤��[" + code + "]����");
//            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
//        }
//    }
//}