package service;

import domain.Item;
import domain.LineItem;
import domain.Order;
import domain.Sequence;
import persistence.ItemDAO;
import persistence.LineItemDAO;
import persistence.OrderDAO;
import persistence.SequenceDAO;
import persistence.impl.ItemDAOImpl;
import persistence.impl.LineItemDAOImpl;
import persistence.impl.OrderDAOImpl;
import persistence.impl.SequenceDAOImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService<OrderDAO> {
    private ItemDAO itemDAO;
    private persistence.OrderDAO orderDAO;
    private LineItemDAO lineItemDAO;
    private SequenceDAO sequenceDAO;

    public OrderService() {
        itemDAO = new ItemDAOImpl();
        orderDAO = new OrderDAOImpl();
        lineItemDAO = new LineItemDAOImpl();
        sequenceDAO = new SequenceDAOImpl();
    }

    public void insertOrder(Order order) {
        order.setOrderId(getNextId("ordernum"));
        for (int i=0; i<order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            String itemId = lineItem.getItemId();
            Integer increment = new Integer(lineItem.getQuantity());
            Map<String, Object> param = new HashMap<String, Object>(2);
            param.put("itemId", itemId);
            param.put("increment", increment);
            itemDAO.updateInventoryQuantity(param);
        }
        orderDAO.insertOrder(order);
        orderDAO.insertOrderStatus(order);
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            lineItem.setOrderId(order.getOrderId());
            lineItemDAO.insertLineItem(lineItem);
        }
    }

    public Order getOrder(int orderId) {
        Order order = orderDAO.getOrder(orderId);
        order.setLineItems(lineItemDAO.getLineItemsByOrderId(orderId));

        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            Item item = itemDAO.getItem(lineItem.getItemId());
            item.setQuantity(itemDAO.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }
        return order;
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderDAO.getOrdersByUsername(username);
    }

    public int getNextId(String name) {
        Sequence sequence = new Sequence(name, -1);
        sequence = (Sequence) sequenceDAO.getSequence(sequence);
        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
                    + " sequence).");
        }
        Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
        sequenceDAO.updateSequence(parameterObject);
        return sequence.getNextId();
    }
}
