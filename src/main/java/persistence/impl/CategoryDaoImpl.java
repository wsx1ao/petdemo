package persistence.impl;

import domain.Category;
import persistence.CategeoryDao;
import persistence.impl.DBUtil;

import java.lang.management.GarbageCollectorMXBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategeoryDao {
    private static final String GET_CATEGORY_LIST = "SELECT CATID AS categoryId, NAME, DESCN AS description FROM GATEGORY";
    private static final String GET_GATEGORY = "SELECT CATID AS categoryId, NAME, DESCN AS description FROM CATEGORY WHERE CATID=?";
    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList=new ArrayList<Category>();
        try{
            Connection connection=DBUtil.getConnection();
            PreparedStatement pStatement=connection.prepareStatement(GET_CATEGORY_LIST);
            ResultSet resultSet=pStatement.executeQuery();
            while(resultSet.next()){
                Category category=new Category();
                category.setCategoryId(resultSet.getString(1));
                category.setName(resultSet.getString(2));
                category.setDescription(resultSet.getString(3));
                categoryList.add(category);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch(Exception e){
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public Category getCategory(String categoryId) {
        Category category = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_GATEGORY);
            preparedStatement.setString(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                category = new Category();
                category.setCategoryId(resultSet.getString(1));
                category.setName(resultSet.getString(2));
                category.setDescription(resultSet.getString(3));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }
}
