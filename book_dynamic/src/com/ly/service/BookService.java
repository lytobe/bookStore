package com.ly.service;

import com.ly.entity.Book;
import com.ly.entity.Page;

import java.util.List;

public interface BookService {
    public void addBook(Book book);
    public void updateBook(Book book);
    public void deleteBookById(Integer bookId);
    public Book queryBookById(Integer bookId);
    public List<Book> queryAll();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
