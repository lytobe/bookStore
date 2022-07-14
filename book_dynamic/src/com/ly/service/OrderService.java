package com.ly.service;

import com.ly.entity.Car;
import com.ly.entity.Order;
import com.ly.entity.OrderItem;

import java.util.List;

public interface OrderService {
    String createOrder(Car car,Integer userId);
    List<Order> queryOrders(Integer userId);
    List<OrderItem> queryOrderItemsByOrderId(String orderId);
    List<Order> queryAll();

    Order queryOrderByOrderId(String orderId);

    void sendOrder(String orderId);
    void receiveOrder(String orderId);
}
