//购物车持久化代码，备用
//package org.csu.mypetstore.service;
//
//import org.csu.mypetstore.domain.Account;
//import org.csu.mypetstore.domain.Cart;
//import org.csu.mypetstore.domain.CartItem;
//import org.csu.mypetstore.domain.Item;
//import org.csu.mypetstore.persistence.CartMapper;
//import org.csu.mypetstore.persistence.ItemMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class CartService {
//    @Autowired
//    private CartMapper cartMapper;
//
//    public CartItem getCartByUsername(String username){
//    return cartMapper.getCartByUsername(username);
//    }
//
//    @Transactional
//    public void insertCartItem(CartItem cartItem, Account account){
//        cartMapper.insertCartItem(cartItem,account);
//    }
//
//    public void updateCart(CartItem cartItem, Account account){
//        cartMapper.updateCart(cartItem,account);
//    }
//
//    public void removeCartItem(CartItem cartItem, Account account){
//        cartMapper.removeCartItem(cartItem,account);
//    }
//}
