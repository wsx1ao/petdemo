package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.persistence.LineItemMapper;
import org.csu.mypetstore.persistence.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private LineItemMapper lineItemMapper;
    @Autowired
    private OrderMapper orderMapper;

    public List<LineItem> getLineItemsByOrderId(int orderId){return lineItemMapper.getLineItemsByOrderId(orderId);}
    public List<Order> getOrdersByUsername(String username){return orderMapper.getOrdersByUsername(username);}
    public Order getOrder(int orderId){return orderMapper.getOrder(orderId);}


    @Transactional
    public void insertLineItem(LineItem lineItem){
     lineItemMapper.insertLineItem(lineItem);
    }
    public void insertOrder(Order order){
        orderMapper.insertOrder(order);
    }
    public void insertOrderStatus(Order order){
        orderMapper.insertOrderStatus(order);
    }

}
