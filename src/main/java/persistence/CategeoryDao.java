package persistence;
import domain.Category;
import java.util.List;
public interface CategeoryDao
{    //select all category
    List<Category> getCategoryList();
    //select a category by ID
    Category getCategory(String categoryId);
}
