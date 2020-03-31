package service;

import domain.*;
import persistence.ItemDAO;
import persistence.impl.*;
import persistence.ProductDao;
import persistence.CategeoryDao;
import java.util.List;
public class CatalogService {
   private CategeoryDao categeoryDao;
   private ProductDao productDao;
    private ItemDAO itemDAO;

   public CatalogService() {
     categeoryDao=new CategoryDaoImpl();
     productDao=new ProductDaoImpl();
     itemDAO = new ItemDAOImpl();
    }
    public List<Category> getCategoryList() {
        return  categeoryDao.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return  categeoryDao.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return productDao.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productDao.getProductListByCategory(categoryId);
    }
    // TODO enable using more than one keyword
    public List<Product> searchProductList(String keyword) {
        return productDao.searchProductList("%" + keyword.toLowerCase() + "%");
    }
    public List<Item> getItemListByProduct(String productId) {
        return itemDAO.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return itemDAO.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return itemDAO.getInventoryQuantity(itemId) > 0;
    }

}
