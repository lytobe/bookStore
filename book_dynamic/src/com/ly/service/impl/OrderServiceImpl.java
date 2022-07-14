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

        System.out.println("orderService������"+Thread.currentThread().getName());

        //������Ψһ�ԣ�ʱ������û�id
        String orderId = System.currentTimeMillis()+""+userId;
        //����һ����������
        Order order = new Order(orderId,new Date(),car.getTotalPrice(),0,userId);
        //���涩��
        orderDao.saveOrder(order);
        //������Ʒ�е�ÿһ����Ʒ��ת��Ϊ������浽���ݿ�
        for (Map.Entry<Integer, CarItem>entry:car.getItems().entrySet()) {
            //��ȡÿһ�����ﳵ�е���Ʒ��
            CarItem  carItem = entry.getValue();
            //ת��Ϊÿһ��������
            OrderItem orderItem = new OrderItem(null, carItem.getName(),carItem.getCount(),carItem.getPrice(),carItem.getTotalPrice(),orderId);
            //���涩������ݿ�
            orderItemDao.saveOrderItem(orderItem);

            Book book = bookDao.queryBookById(carItem.getId());
            book.setSales(book.getSales()+carItem.getCount());
            book.setStock(book.getStock()- carItem.getCount());
            bookDao.updateBook(book);
        }
        car.clear();//���˺���չ��ﳵ
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
