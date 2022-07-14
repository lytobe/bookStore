package com.ly.test;

import com.ly.dao.OrderDao;
import com.ly.dao.impl.OrderDaoImpl;
import com.ly.entity.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoTest {
    OrderDao orderDao = new OrderDaoImpl();
    @Test
    void saveOrder() {

        orderDao.saveOrder(new Order("123466",new Date(),new BigDecimal(22),0,1));
    }
    @Test
    void queryAll(){
        System.out.println(orderDao.queryAllOrder(1));
    }

    @Test
    void queryOrders(){
        for (Order o: orderDao.queryAllOrders()) {
            System.out.println(o);
        }
    }

    @Test
    void queryOrderByOrderId(){
        Order order = orderDao.queryOrderByOrderId("123456");
        System.out.println(order);
    }
}