package com.ly.service.impl;

import com.ly.dao.BookDao;
import com.ly.dao.impl.BookDaoImpl;
import com.ly.entity.Book;
import com.ly.entity.Page;
import com.ly.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public void deleteBookById(Integer bookId) {
        bookDao.deleteBook(bookId);
    }

    @Override
    public Book queryBookById(Integer bookId) {

        return bookDao.queryBookById(bookId);
    }

    @Override
    public List<Book> queryAll() {
        return bookDao.queryAll();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();



        //设置每页显示的数量
        page.setPageSize(pageSize);

        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //设计总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount/pageSize;
        if (pageTotalCount%pageSize>0){
            pageTotal+=1;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);

        //求当前页数据
        int begin = (page.getPageNo()-1)*pageSize;
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);

        //设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<Book>();

        //设置每页显示的数量
        page.setPageSize(pageSize);

        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        //设计总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount/pageSize;
        if (pageTotalCount%pageSize>0){
            pageTotal+=1;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);

        //求当前页数据
        int begin = (page.getPageNo()-1)*pageSize;
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);

        //设置当前页数据
        page.setItems(items);

        return page;
    }
}
