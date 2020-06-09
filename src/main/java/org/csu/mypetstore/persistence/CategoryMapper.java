package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Category;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryMapper {
    List<Category> getCategoryList();

    Category getCategory(String categoryId);

    void insertCategory(Category category);

    void deleteCategoryById(String categoryId);

    void deleteCategoryById2(String categoryId);

    void deleteCategoryById3(String categoryId);

    void deleteCategoryById4(String categoryId);

    void updateCategory(Category category);
}
