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



        //����ÿҳ��ʾ������
        page.setPageSize(pageSize);

        //���ܼ�¼��
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //����ܼ�¼��
        page.setPageTotalCount(pageTotalCount);
        //����ҳ��
        Integer pageTotal = pageTotalCount/pageSize;
        if (pageTotalCount%pageSize>0){
            pageTotal+=1;
        }
        //������ҳ��
        page.setPageTotal(pageTotal);
        //���õ�ǰҳ��
        page.setPageNo(pageNo);

        //��ǰҳ����
        int begin = (page.getPageNo()-1)*pageSize;
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);

        //���õ�ǰҳ����
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<Book>();

        //����ÿҳ��ʾ������
        page.setPageSize(pageSize);

        //���ܼ�¼��
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        //����ܼ�¼��
        page.setPageTotalCount(pageTotalCount);
        //����ҳ��
        Integer pageTotal = pageTotalCount/pageSize;
        if (pageTotalCount%pageSize>0){
            pageTotal+=1;
        }
        //������ҳ��
        page.setPageTotal(pageTotal);
        //���õ�ǰҳ��
        page.setPageNo(pageNo);

        //��ǰҳ����
        int begin = (page.getPageNo()-1)*pageSize;
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);

        //���õ�ǰҳ����
        page.setItems(items);

        return page;
    }
}
