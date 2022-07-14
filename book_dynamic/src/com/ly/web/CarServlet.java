package com.ly.web;

import com.ly.entity.Book;
import com.ly.entity.Car;
import com.ly.entity.CarItem;
import com.ly.service.BookService;
import com.ly.service.impl.BookServiceImpl;
import com.ly.utils.WebBeanUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CarServlet extends  BaseServlet{
    BookService bookService = new BookServiceImpl();

    /**
     * ���빺�ﳵ
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("���빺�ﳵ");
//        System.out.println("��Ʒ���"+req.getParameter("id"));
        //��ȡ��Ʒ���
        int id = WebBeanUtil.strToInt(req.getParameter("id"),0);
        //����bookeservice���һ��ͼ����Ϣ
        Book book = bookService.queryBookById(id);
        //��ͼ����Ϣת��Ϊ��Ʒ��caritem
        CarItem carItem = new CarItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //����car.addItem()�����ͼ����Ʒ
        Car car = (Car) req.getSession().getAttribute("car");
        if (car==null){
            car = new Car();
            req.getSession().setAttribute("car",car);
        }
        car.addItem(carItem);
        //�ض�����Ʒ�б�
//        resp.sendRedirect(req.getContextPath());
        System.out.println("����ͷreferer��ֵΪ"+req.getHeader("Referer"));
        resp.sendRedirect(req.getHeader("Referer"));

        System.out.println(car);
        req.getSession().setAttribute("lastName",carItem.getName());
    }


    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebBeanUtil.strToInt(req.getParameter("id"),0);
        Car car = (Car) req.getSession().getAttribute("car");
        if (car!=null){
            car.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car car = (Car) req.getSession().getAttribute("car");
        if (car!=null){
            car.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebBeanUtil.strToInt(req.getParameter("id"),0);
        int count = WebBeanUtil.strToInt(req.getParameter("count"),1);
        Car car = (Car) req.getSession().getAttribute("car");
        if (car!=null){
            car.update(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
