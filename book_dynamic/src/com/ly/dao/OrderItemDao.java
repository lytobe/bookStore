package com.ly.dao;


import com.ly.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
    int saveOrderItem(OrderItem orderItem);
    List<OrderItem> queryOrderItemsByOrderId(String orderId);

}
