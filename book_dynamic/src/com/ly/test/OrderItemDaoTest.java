package com.ly.test;

import com.ly.dao.OrderItemDao;
import com.ly.dao.impl.BaseDaoImpl;
import com.ly.dao.impl.OrderItemDaoImpl;
import com.ly.entity.OrderItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemDaoTest {
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Test
    void saveOrderItem() {

        orderItemDao.saveOrderItem(new OrderItem(null,"Java从入门到精通",1,new BigDecimal(20),new BigDecimal(20),"123456"));

    }

    @Test
    void queryOrderItemsByOrderId(){
        System.out.println(orderItemDao.queryOrderItemsByOrderId("123456"));
    }
}