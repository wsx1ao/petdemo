package persistence.impl;

import domain.LineItem;
import persistence.impl.DBUtil;
import persistence.LineItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LineItemDAOImpl implements LineItemDAO {
    private static final String GET_LINEITEMS_BY_ORDERID = "SELECT ORDERID, LINENUM AS lineNumber, ITEMID, QUANTITY, UNITPRICE FROM LINEITEM WHERE ORDERID = ?";
    private static final String INSERT_LINEITEM = "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE) VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List<LineItem> lineItemList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_LINEITEMS_BY_ORDERID);
            preparedStatement.setString(1, String.valueOf(orderId));
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                LineItem lineItem = new LineItem();
                lineItem.setOrderId(resultSet.getInt(1));
                lineItem.setLineNumber(resultSet.getInt(2));
                lineItem.setItemId(resultSet.getString(3));
                lineItem.setQuantity(resultSet.getInt(4));
                lineItem.setUnitPrice(resultSet.getBigDecimal(5));

                lineItemList.add(lineItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineItemList;
    }

    @Override
    public void insertLineItem(LineItem lineItem) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LINEITEM);
            preparedStatement.setString(1, String.valueOf(lineItem.getOrderId()));
            preparedStatement.setString(2, String.valueOf(lineItem.getLineNumber()));
            preparedStatement.setString(3, lineItem.getItemId());
            preparedStatement.setString(4, String.valueOf(lineItem.getQuantity()));
            preparedStatement.setString(5, String.valueOf(lineItem.getUnitPrice()));

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
