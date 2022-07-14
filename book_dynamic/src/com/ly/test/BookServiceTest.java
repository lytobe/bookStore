package com.ly.test;

import com.ly.entity.Book;
import com.ly.entity.Page;
import com.ly.service.BookService;
import com.ly.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


class BookServiceTest {
    BookService bookService = new BookServiceImpl();
    @Test
    void addBook() {
        bookService.addBook(new Book(null, "����", new BigDecimal(9999), "����", 1000, 0, null));
    }

    @Test
    void updateBook() {
        bookService.updateBook(new Book(51, "��һ����", new BigDecimal(9999), "����", 1000000, 0, null));

    }

    @Test
    void deleteBookById() {
        bookService.deleteBookById(52);
    }

    @Test
    void queryBookById() {
        System.out.println( bookService.queryBookById(51));
    }

    @Test
    void queryAll() {
        for (Book book:bookService.queryAll()) {
            System.out.println(book);
        }
    }
    @Test
    void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }
    @Test
    void pageByPrice(){
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE,10,50));
    }
}