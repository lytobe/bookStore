package com.ly.test;

import com.ly.dao.BookDao;
import com.ly.dao.impl.BookDaoImpl;
import com.ly.entity.Book;
import com.ly.entity.Page;
import junit.framework.TestCase;

import java.math.BigDecimal;
import java.sql.SQLOutput;

public class BookDaoTest extends TestCase {
private BookDao bookDao = new BookDaoImpl();

    public void testAddBook() {
        //int i = bookDao.addBook(new Book(null, "哈利波特", new BigDecimal(9999), "哈利", 1000, 0, null));
        System.out.println(bookDao.addBook(new Book(null, "哈利波特", new BigDecimal(9999), "哈利", 1000, 0, null)));
    }

    public void testUpdateBook() {
        System.out.println(bookDao.updateBook(new Book(51, "哈利", new BigDecimal(9999), "波特", 1000, 0, null)));
    }

    public void testDeleteBook() {
        System.out.println(bookDao.deleteBook(50));
    }

    public void testQueryBookById() {
        System.out.println(bookDao.queryBookById(51));
    }

    public void testQueryAll() {
         for (Book book:bookDao.queryAll()) {
             System.out.println(book);
        }
    }

    public void testQueryForPageTotalCount(){
        System.out.println(bookDao.queryForPageTotalCount());

    }

    public void testQueryForPageItems() {
        for (Book book : bookDao.queryForPageItems(24, Page.PAGE_SIZE)) {
            System.out.println(book);
        }
    }

    public void testQueryForPageTotalCountByPrice(){
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));

    }

    public void testQueryForPageItemsByPrice() {
        for (Book book : bookDao.queryForPageItemsByPrice(0,Page.PAGE_SIZE,10,50 )) {
            System.out.println(book);
        }
    }
}