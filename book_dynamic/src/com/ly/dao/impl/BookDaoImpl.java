package com.ly.dao.impl;

import com.ly.dao.BookDao;
import com.ly.entity.Book;

import java.util.List;

public class BookDaoImpl extends BaseDaoImpl implements BookDao {
    @Override
    public int addBook(Book book) {
//        ('name','price','author','sales','stock','img_path')
        String sql = "insert into t_book(name,price,author,sales,stock,img_path) values (?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int updateBook(Book book) {
        System.out.println("bookdao³ÌÐòÔÚ"+Thread.currentThread().getName());

        String sql = "update t_book set name=?,price=?,author=?,sales=?,stock=?,img_path=? where id = ?";
        return update(sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public int deleteBook(Integer bookId) {
        String sql = "delete from t_book where id = ?";
        return update(sql, bookId);
    }

    @Override
    public Book queryBookById(Integer bookId) {
        String sql = "select * from t_book where id=?";
        return queryForOne(Book.class, sql, bookId);
    }

    @Override
    public List<Book> queryAll() {
        String sql = "select * from t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select id,name,author,price,sales,stock,img_path from t_book limit ?,?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min,int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql,min,max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select id,name,author,price,sales,stock,img_path from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class, sql,min,max, begin, pageSize);
    }
}
