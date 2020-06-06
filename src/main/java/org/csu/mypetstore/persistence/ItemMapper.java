package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ItemMapper {
    void updateInventoryQuantity(String itemId,int quantity);

    int getInventoryQuantity(String itemId);

    List<Item> getItemListByProduct(String productId);

    Item getItem(String itemId);

    List<Item> getAllItem();

    void DeleteItemById1(String itemId);
    void DeleteItemById2(String itemId);

    void insertItem1(Item item);
    void insertItem2(String itemId, int quantity);

    void updateItem1(Item item);
    void updateItem2(String itemId,int quantity);
}
