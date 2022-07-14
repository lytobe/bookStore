package com.ly.test;

import com.ly.entity.Car;
import com.ly.entity.CarItem;
import com.ly.entity.Order;
import com.ly.service.OrderService;
import com.ly.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


class OrderServiceTest {
    OrderService orderService = new OrderServiceImpl();
    @Test
    void createOrder() {
        Car car = new Car();
        car.addItem(new CarItem(1,"Java�����ŵ���ͨ",1,new BigDecimal(22),new BigDecimal(66)));


        System.out.println("�����������ǣ�"+orderService.createOrder(car,1));
    }
    @Test
    void queryAll(){
        System.out.println(orderService.queryOrders(1));
    }

    @Test
    void queryAllOrderItems(){
        System.out.println(orderService.queryOrderItemsByOrderId("123456"));
    }

    @Test
    void queryAllOrder(){
        for (Order o:orderService.queryAll()) {
            System.out.println(o);
        }
    }
    @Test
    void queryOrderByOrderId(){
        Order order = orderService.queryOrderByOrderId("123456");
        System.out.println(order);
    }
}