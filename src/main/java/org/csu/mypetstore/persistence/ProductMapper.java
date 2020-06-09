package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {

    List<Product> getProductListByCategory(String categoryId);

    Product getProduct(String productId);

    List<Product> searchProductList(String keywords);

    void insertProduct1(Product product);

    void deleteProductById1(String productId);

    void deleteProductById2(String productId);

    void deleteProductById3(String productId);

    void updateProduct1(Product product);

    List<Product> getAllProduct();
}
