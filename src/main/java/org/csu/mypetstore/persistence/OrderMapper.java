package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Order;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderMapper {
    List<Order> getOrdersByUsername(String username);
    List<Order> getallOrders();
    Order getOrder(int orderId);
    void insertOrder(Order order);
    void insertOrderStatus(Order order);
    void updateorder(Order order);
    void deleteOrder(int orderId);
}
