package com.ly.dao.impl;

import com.ly.dao.OrderItemDao;
import com.ly.entity.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDaoImpl implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        System.out.println("orderItemDao³ÌÐòÔÚ"+Thread.currentThread().getName());

        String sql = "insert into orderitem(name,count,price,total_price,order_id) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "select name,count,price,total_price as totalPrice from orderitem where order_id=?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
