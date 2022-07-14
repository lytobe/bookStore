package com.ly.web;

import com.ly.entity.*;
import com.ly.service.OrderService;
import com.ly.service.impl.OrderServiceImpl;
import com.ly.utils.WebBeanUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{
    OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Car car = (Car) req.getSession().getAttribute("car");
        //获取用户id
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        System.out.println("orderServlet程序在"+Thread.currentThread().getName());

        Integer userId = loginUser.getId();
        //调用order service生成订单
        String orderId = orderService.createOrder(car,userId);

//        req.setAttribute("orderId",orderId);

//        //请求转发到checkout.jsp中
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);

        //解决表单重复提交问题
        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }


    protected void queryAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        int userId = loginUser.getId();
        List<Order> order = (List<Order>) orderService.queryOrders(userId);
        req.setAttribute("order",order);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    protected void queryOrderItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItem = (List<OrderItem>) orderService.queryOrderItemsByOrderId(orderId);
        System.out.println(orderItem);
        req.setAttribute("orderItem",orderItem);
        req.getRequestDispatcher("/pages/order/order_details.jsp").forward(req,resp);
    }


    protected void allOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = (List<Order>) orderService.queryAll();
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }


    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        req.getSession().setAttribute("orderId",orderId);
        Order order = orderService.queryOrderByOrderId(orderId);
        orderService.sendOrder(orderId);
        req.getRequestDispatcher("orderServlet?action=allOrders").forward(req,resp);

    }

    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        int userId = loginUser.getId();
        Order order = orderService.queryOrderByOrderId(orderId);
        orderService.receiveOrder(orderId);
        req.getRequestDispatcher("orderServlet?action=queryAllOrders&userId=userId").forward(req,resp);
    }
}
