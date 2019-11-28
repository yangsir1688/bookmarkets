package cn.javabs.bookmarket.dao;

import cn.javabs.bookmarket.entity.Category;

import java.util.List;

public interface CategoryDao {
    int addCategory(Category category);
    int delCategory(int id);
    int updateCategory(Category category);

    Category getCategoryById(int id);

    List<Category> getAllCategories();

}
