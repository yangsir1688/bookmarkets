package cn.javabs.bookmarket.dao.impl;

import cn.javabs.bookmarket.dao.BookDao;
import cn.javabs.bookmarket.entity.Book;
import cn.javabs.bookmarket.exception.BookDaoImplException;
import cn.javabs.bookmarket.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl implements BookDao {

    QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());

    @Override
    public int addBook(Book book) {
        try {
            return qr.update("insert into book(name,author,publish,price,path,photoName,categoryId) values(?,?,?,?,?,?,?)",
                         book.getName(),
                         book.getAuthor(),
                         book.getPublish(),
                         book.getPrice(),
                         book.getPath(),
                         book.getPhotoName(),
                         book.getCategory().getId()
                    );
        } catch (SQLException e) {
            throw new BookDaoImplException(e);
        }
    }

    @Override
    public int delBook(int id) {
        try {
            return qr.update("delete from book where id = ?",id  );
        } catch (SQLException e) {
            throw new BookDaoImplException(e);
        }
    }

    @Override
    public int updateBook(Book book) {
        try {
            return qr.update("update book set name = ?,author = ?,publish = ?,price = ?,path = ?,photoName = ?,categoryId = ? where id = ?",
                    book.getName(),
                    book.getAuthor(),
                    book.getPublish(),
                    book.getPrice(),
                    book.getPath(),
                    book.getPhotoName(),
                    book.getCategory().getId(),
                    book.getId()
            );
        } catch (SQLException e) {
            throw new BookDaoImplException(e);
        }
    }

    @Override
    public Book getBookById(int id) {
        try {
            return qr.query("select * from book where id = ?",new BeanHandler<Book>(Book.class),id);
        } catch (SQLException e) {
            throw new BookDaoImplException(e);
        }
    }

    @Override
    public int findPageBookNumber(int categoryId) {
        try {
            Object obj = qr.query("select count(*) from book where categoryId = ?", new ScalarHandler(1), categoryId);
            Long num = (Long) obj;
            int number = num.intValue();
            return number;
        } catch (SQLException e) {
            throw new BookDaoImplException(e);
        }
    }

    /**
     * 查询全部图书
     * |--  分页 需要两个参数
     *
     * @param startIndex 开始的索引
     * @param pageSize   一页有多少条
     * @return
     */
    @Override
    public List<Book> findAllBookRecords(int startIndex, int pageSize) {
        try {
            List<Book> bookList =  qr.query("select * from book limit ?,?", new BeanListHandler<Book>(Book.class),startIndex,pageSize);
            return bookList;
        } catch (SQLException e) {
            throw new BookDaoImplException(e);
        }
    }

    /**
     * 查询某分类下的全部图书
     *
     * @param startIndex 开始的索引
     * @param pageSize   一页有多少条
     * @param categoryId 分类的id
     * @return
     */
    @Override
    public List<Book> findPageBooks(int startIndex, int pageSize, int categoryId) {
        try {
            List<Book> bookList =  qr.query("select * from book where categoryId = ? limit ?,?", new BeanListHandler<Book>(Book.class),categoryId,startIndex,pageSize);
            return bookList;
        } catch (SQLException e) {
            throw new BookDaoImplException(e);
        }
    }
}
