package com.ly.dao;


import com.ly.entity.Book;

import java.util.List;

public interface BookDao {
    int addBook(Book book);

    int updateBook(Book book);

    int deleteBook(Integer bookId);

    Book queryBookById(Integer bookId);

    List<Book> queryAll();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min,int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
