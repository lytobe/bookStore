package com.ly.web;

import com.ly.entity.Book;
import com.ly.entity.Page;
import com.ly.service.BookService;
import com.ly.service.impl.BookServiceImpl;
import com.ly.utils.WebBeanUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1��ȡ����Ĳ���pageNo��pageSize
        System.out.println("������clientBookServlet");
        int pageNo = WebBeanUtil.strToInt(req.getParameter("pageNo"), 1);
        int pageSize = WebBeanUtil.strToInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2����BookService.page(pageNo,pagesize):Page����
        Page<Book> page = bookService.page(pageNo, pageSize);
        //3����Page����request����
        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page", page);
        //4����ת����pages/manager/book_manager.jspҳ��
        System.out.println("��Ҫת����");
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
        System.out.println("ת����");
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1��ȡ����Ĳ���pageNo��pageSize
        System.out.println("������clientBookServlet");
        int pageNo = WebBeanUtil.strToInt(req.getParameter("pageNo"), 1);
        int pageSize = WebBeanUtil.strToInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebBeanUtil.strToInt(req.getParameter("min"), 0);
        int max = WebBeanUtil.strToInt(req.getParameter("max"),Integer.MAX_VALUE);
        //

        //2����BookService.page(pageNo,pagesize):Page����
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);

        StringBuilder builder = new StringBuilder("client/bookServlet?action=pageByPrice");

        //�������С�۸�Ĳ�����׷�ӵ���ҳ���Ĳ�����
        if (req.getParameter("min")!=null){
            builder.append("&min=").append(req.getParameter("min"));
        }
        //�������С�۸�Ĳ�����׷�ӵ���ҳ���Ĳ�����
        if (req.getParameter("max")!=null){
            builder.append("&max=").append(req.getParameter("max"));
        }

        //3����Page����request����
        page.setUrl(builder.toString());
        req.setAttribute("page", page);
        //4����ת����pages/manager/book_manager.jspҳ��
        System.out.println("��Ҫת����");
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
        System.out.println("ת����");
    }
}
