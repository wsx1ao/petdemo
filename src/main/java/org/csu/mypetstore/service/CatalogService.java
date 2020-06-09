package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.CategoryMapper;
import org.csu.mypetstore.persistence.ItemMapper;
import org.csu.mypetstore.persistence.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CatalogService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ItemMapper itemMapper;

    public Category getCategory(String categoryId) {
        return categoryMapper.getCategory(categoryId);
    }

    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryList();
    }

    public Product getProduct(String productId) {
        return productMapper.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productMapper.getProductListByCategory(categoryId);
    }
    public List<Product> searchProductList(String keyword) {
        return productMapper.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    public List<Item> getItemListByProduct(String productId){
        return itemMapper.getItemListByProduct(productId);
    }

    public Item getItem(String itemId){
        return itemMapper.getItem(itemId);
    }

    public List<Item> getAllItem(){return itemMapper.getAllItem();}

    public List<Product> getAllProduct(){return productMapper.getAllProduct();}

    public void insertItem1(Item item) {itemMapper.insertItem1(item);}
    public void insertItem2(String itemId, int quantity) {itemMapper.insertItem2(itemId, quantity);}

    public void DeleteItemById1(String itemId){ itemMapper.DeleteItemById1(itemId);}
    public void DeleteItemById2(String itemId){ itemMapper.DeleteItemById2(itemId);}

    public void updateItem1(Item item) {itemMapper.updateItem1(item);}
    public void updateItem2(String itemId,int quantity) {itemMapper.updateItem2(itemId,quantity);}

    public void insertProduct1(Product product) {productMapper.insertProduct1(product);}

    public void deleteProductById1(String productId){ productMapper.deleteProductById1(productId);}
    public void deleteProductById2(String productId){ productMapper.deleteProductById2(productId);}
    public void deleteProductById3(String productId){ productMapper.deleteProductById3(productId);}

    public void updateProduct1(Product product){productMapper.updateProduct1(product);}

    public void insertCategory(Category category) {categoryMapper.insertCategory(category);}

    public void deleteCategoryById(String categoryId) {categoryMapper.deleteCategoryById(categoryId);}
    public void deleteCategoryById2(String categoryId) {categoryMapper.deleteCategoryById2(categoryId);}
    public void deleteCategorybyId3(String categoryId){
        categoryMapper.deleteCategoryById3(categoryId);
    }
    public void deleteCategorybyId4(String categoryId){
        categoryMapper.deleteCategoryById3(categoryId);
    }
    public void updateCategory(Category category) {categoryMapper.updateCategory(category);}

    public boolean isItemInStock(String itemId){
        return itemMapper.getInventoryQuantity(itemId) > 0;
    }

    public void updateInventoryQuantity(String itemId,int quantity){
        itemMapper.updateInventoryQuantity(itemId,quantity);
    }
}
