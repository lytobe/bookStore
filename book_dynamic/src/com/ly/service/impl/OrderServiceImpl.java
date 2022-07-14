package com.ly.service.impl;

import com.ly.dao.BookDao;
import com.ly.dao.OrderDao;
import com.ly.dao.OrderItemDao;
import com.ly.dao.impl.BookDaoImpl;
import com.ly.dao.impl.OrderDaoImpl;
import com.ly.dao.impl.OrderItemDaoImpl;
import com.ly.entity.*;
import com.ly.service.BookService;
import com.ly.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
   private OrderDao orderDao = new OrderDaoImpl();
   private OrderItemDao orderItemDao = new OrderItemDaoImpl();
   private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Car car, Integer userId) {

        System.out.println("orderService程序在"+Thread.currentThread().getName());

        //订单号唯一性，时间戳加用户id
        String orderId = System.currentTimeMillis()+""+userId;
        //创建一个订单对象
        Order order = new Order(orderId,new Date(),car.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);
        //遍历商品中的每一个商品项转换为订单项保存到数据库
        for (Map.Entry<Integer, CarItem>entry:car.getItems().entrySet()) {
            //获取每一个购物车中的商品项
            CarItem  carItem = entry.getValue();
            //转化为每一个订单项
            OrderItem orderItem = new OrderItem(null, carItem.getName(),carItem.getCount(),carItem.getPrice(),carItem.getTotalPrice(),orderId);
            //保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            Book book = bookDao.queryBookById(carItem.getId());
            book.setSales(book.getSales()+carItem.getCount());
            book.setStock(book.getStock()- carItem.getCount());
            bookDao.updateBook(book);
        }
        car.clear();//结账后清空购物车
        return orderId;
    }

    @Override
    public List<Order> queryOrders(Integer userId) {
        return orderDao.queryAllOrder(userId);
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }

    @Override
    public List<Order> queryAll() {
        return orderDao.queryAllOrders();
    }

    @Override
    public Order queryOrderByOrderId(String orderId) {
        return orderDao.queryOrderByOrderId(orderId);
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.sendOrder(orderId);
    }

    @Override
    public void receiveOrder(String orderId) {
        orderDao.receiveOrder(orderId);
    }
}
