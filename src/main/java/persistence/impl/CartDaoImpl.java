package persistence.impl;

import domain.Account;
import domain.Cart;
import domain.CartItem;
import domain.Item;
import persistence.CartDao;
import persistence.ItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CartDaoImpl implements CartDao {
    private static final String GET_CART_BY_USERNAME = "select ITEMID , QUANTITY from CARTITEM where USERID = ?";
    private static final String INSERT_CARTITEM = "insert into CARTITEM (USERID, ITEMID, QUANTITY) values (?, ?, ?)";
    private static final String UPDATE_CART = "update CARTITEM set QUANTITY = ? where USERID = ? and ITEMID = ?";
    private static final String DELETE_CARTITEM = "delete from CARTITEM where USERID = ? and ITEMID = ?";
    @Override
    public Cart getCartByUsername(String username) {
        Cart cart = new Cart();
        ItemDAO itemDAO = new ItemDAOImpl();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CART_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Item item = itemDAO.getItem(resultSet.getString(1));
                int quantity = resultSet.getInt(2);
                cart.addItem(item, itemDAO.getInventoryQuantity(resultSet.getString(1)) > 0, quantity);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public void insertCartItem(CartItem cartItem, Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARTITEM);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, cartItem.getItem().getItemId());
            preparedStatement.setString(3, String.valueOf(cartItem.getQuantity()));
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCart(CartItem cartItem, Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART);
            preparedStatement.setString(1, String.valueOf(cartItem.getQuantity()));
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, cartItem.getItem().getItemId());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCartItem(CartItem cartItem, Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CARTITEM);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, cartItem.getItem().getItemId());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
