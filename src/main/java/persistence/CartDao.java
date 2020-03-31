package persistence;
import domain.Account;
import domain.Cart;
import domain.CartItem;
public interface CartDao {
    public Cart getCartByUsername(String username);

    public void insertCartItem(CartItem cartItem, Account account);

    public void updateCart(CartItem cartItem, Account account);

    public void removeCartItem(CartItem cartItem, Account account);
}
