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
        //1获取请求的参数pageNo和pageSize
        System.out.println("经过了clientBookServlet");
        int pageNo = WebBeanUtil.strToInt(req.getParameter("pageNo"), 1);
        int pageSize = WebBeanUtil.strToInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2调用BookService.page(pageNo,pagesize):Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        //3保存Page对象到request域中
        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page", page);
        //4请求转发到pages/manager/book_manager.jsp页面
        System.out.println("快要转发了");
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
        System.out.println("转发后");
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1获取请求的参数pageNo和pageSize
        System.out.println("经过了clientBookServlet");
        int pageNo = WebBeanUtil.strToInt(req.getParameter("pageNo"), 1);
        int pageSize = WebBeanUtil.strToInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebBeanUtil.strToInt(req.getParameter("min"), 0);
        int max = WebBeanUtil.strToInt(req.getParameter("max"),Integer.MAX_VALUE);
        //

        //2调用BookService.page(pageNo,pagesize):Page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);

        StringBuilder builder = new StringBuilder("client/bookServlet?action=pageByPrice");

        //如果有最小价格的参数，追加到分页条的参数中
        if (req.getParameter("min")!=null){
            builder.append("&min=").append(req.getParameter("min"));
        }
        //如果有最小价格的参数，追加到分页条的参数中
        if (req.getParameter("max")!=null){
            builder.append("&max=").append(req.getParameter("max"));
        }

        //3保存Page对象到request域中
        page.setUrl(builder.toString());
        req.setAttribute("page", page);
        //4请求转发到pages/manager/book_manager.jsp页面
        System.out.println("快要转发了");
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
        System.out.println("转发后");
    }
}
