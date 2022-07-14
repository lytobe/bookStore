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
import java.util.List;

public class BookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebBeanUtil.strToInt(req.getParameter("pageNo"), 0);
        pageNo+=1;
        Book book = WebBeanUtil.copyParameterToBean(req.getParameterMap(), new Book());

        bookService.addBook(book);

        // req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);

    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Book book = WebBeanUtil.copyParameterToBean(req.getParameterMap(), new Book());
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebBeanUtil.strToInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        req.setAttribute("book", book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebBeanUtil.strToInt(req.getParameter("id"), 0);
        bookService.deleteBookById(id);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //ͨ��bookservice��ѯȫ��ͼ��
        List<Book> books = bookService.queryAll();
        //��ȫ�����ݴ浽req����
        req.setAttribute("books", books);
        //����ת��
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1��ȡ����Ĳ���pageNo��pageSize
        int pageNo = WebBeanUtil.strToInt(req.getParameter("pageNo"), 1);
        int pageSize = WebBeanUtil.strToInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2����BookService.page(pageNo,pagesize):Page����
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3����Page����request����
        req.setAttribute("page", page);
        //4����ת����pages/manager/book_manager.jspҳ��
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
