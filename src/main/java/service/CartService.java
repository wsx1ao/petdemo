package service;

import domain.*;
import persistence.ItemDAO;
import persistence.CartDao;
import persistence.impl.CartDaoImpl;
import persistence.impl.ItemDAOImpl;
import java.util.List;

public class CartService {
    private ItemDAO ItemDAO;
    private CartDao CartDao;
    public CartService() {
        CartDao = new CartDaoImpl();
        ItemDAO = new ItemDAOImpl();

    }
    public Cart getCartByUsername(String username) {
        return CartDao.getCartByUsername(username);
    }
    public void insertCartItem(CartItem cartItem, Account account) {
        CartDao.insertCartItem(cartItem, account);
    }
    public void updateCart(CartItem cartItem, Account account) {
        CartDao.updateCart(cartItem, account);
    }
    public void removeCartItem(CartItem cartItem, Account account) { CartDao.removeCartItem(cartItem, account); }
    public Item getItem(String itemId) {
        return ItemDAO.getItem(itemId);
    }
    public boolean isItemInStock(String itemId) {
        return ItemDAO.getInventoryQuantity(itemId) > 0;
    }
}
