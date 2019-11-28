package cn.javabs.bookmarket.dao.impl;

import cn.javabs.bookmarket.dao.CategoryDao;
import cn.javabs.bookmarket.entity.Category;
import cn.javabs.bookmarket.exception.CategoryDaoImplException;
import cn.javabs.bookmarket.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.xml.ws.handler.Handler;
import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());

    @Override
    public int addCategory(Category category) {
        try {
            int rows = qr.update("insert into category(name,description) values (?,?)",
                    category.getName(), category.getDescription());
            return rows;
        } catch (SQLException e) {
            throw new CategoryDaoImplException(e);
        }
    }

    @Override
    public int delCategory(int id) {
        try {
            int rows = qr.update("delete from category where id = ?",id);
            return rows;
        } catch (SQLException e) {
            throw new CategoryDaoImplException(e);
        }
    }

    @Override
    public int updateCategory(Category category) {
        try {
            int rows = qr.update("uodate category set name = ?,description = ? where id = ?",
                    category.getName(), category.getDescription(),category.getId());
            return rows;
        } catch (SQLException e) {
            throw new CategoryDaoImplException(e);
        }
    }

    @Override
    public Category getCategoryById(int id) {
        try {
            Category category = qr.query("select * from category where id = ?",new BeanHandler<Category>(Category.class),id);
            return category;
        } catch (SQLException e) {
            throw new CategoryDaoImplException(e);
        }
    }

    @Override
    public List<Category> getAllCategories() {
        try {
            List<Category> categoryList = qr.query("select * from category ",new BeanListHandler<Category>(Category.class));
            return categoryList;
        } catch (SQLException e) {
            throw new CategoryDaoImplException(e);
        }
    }
}
