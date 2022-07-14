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
        req.getSession().invalidate();//����session
        resp.sendRedirect(req.getContextPath());
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");//��������ı��뷽ʽ�������ڶ�ȡ���������������֮ǰ��������Ч��
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = WebBeanUtil.copyParameterToBean(req.getParameterMap(),new User());

        User loginUser = userService.login(new User(null, username, password, null));

        if (loginUser == null) {
            req.setAttribute("msg", "�û������������");
            req.setAttribute("username", username);

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user",loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1����ȡ����Ĳ���
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);//��ȡsession�е���֤��
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);//ɾ����֤��
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        // 2����� ��֤���Ƿ���ȷ === д��,Ҫ����֤��Ϊ:abcde
//        User user = new User();
//        WebBeanUtil.copyParameterToBean(req.getParameterMap(),user);
        //�Ż��󣨹�����ʹ�÷��ͣ�
        User user = WebBeanUtil.copyParameterToBean(req.getParameterMap(),new User());

//        try {
//            BeanUtils.populate(user,req.getParameterMap());
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

        if (token.equalsIgnoreCase(code)) {
            // 3����� �û����Ƿ����
            if (userService.exitsUsername(username)) {
                System.out.println("�û���[" + username + "]�Ѵ���!");
                // ����ע��ҳ��
                req.setAttribute("msg", "�û����Ѵ���");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                // ����
                // ���� Service ���浽���ݿ�
                userService.register(new User(null, username, password, email));
                // ����ע��ɹ�ҳ�� regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "��֤�����");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("��֤��[" + code + "]����");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

}
