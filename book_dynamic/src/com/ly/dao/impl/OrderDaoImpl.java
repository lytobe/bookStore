package com.ly.dao.impl;

import com.ly.dao.OrderDao;
import com.ly.entity.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    @Override
    public int saveOrder(Order order) {

        System.out.println("orderDao³ÌÐòÔÚ"+Thread.currentThread().getName());

        String sql = "INSERT INTO t_order(order_id,create_time,price,status,user_id) VALUES(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryAllOrder(Integer userId) {
        String sql = "select order_id as orderId,create_time as createTime,price,status from t_order where user_id=?";
        return queryForList(Order.class,sql,userId);
    }

    @Override
    public List<Order> queryAllOrders() {
        String sql = "select order_id as orderId,create_time as createTime,price,status,user_id as userId from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public Order queryOrderByOrderId(String orderId) {
        String sql = "select create_time as createTime,price,status,user_id as userId from t_order where order_id = ?";
        return queryForOne(Order.class,sql,orderId);
    }

    @Override
    public void sendOrder(String orderId) {
        String sql = "update t_order set status=1 where order_id=?";
        update(sql,orderId);
    }

    @Override
    public void receiveOrder(String orderId) {
        String sql = "update t_order set status=2 where order_id=?";
        update(sql,orderId);
    }

}
