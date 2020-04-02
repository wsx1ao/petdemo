package org.csu.mypetstore;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.service.AccountService;
//import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
@MapperScan("org.csu.mypetstore.persistence")
class MypetstoreApplicationTests {
    @Autowired
    CatalogService catalogService;
    @Autowired
    AccountService accountService;
    @Autowired
    OrderService orderService;
//    @Autowired
//    CartService cartService;

    @Test
    void contextLoads() {
    }

    @Test
    void testCatagory(){
        Category c=catalogService.getCategory("BIRDS");
        System.out.println(c.getName()+","+ c.getDescription());
    }
    @Test
    void testProdect(){
        List<Product> productList=catalogService.getProductListByCategory("BIRDS");
        System.out.print(productList.size());
    }
    @Test
    void testItem(){
       Item item=catalogService.getItem("EST-14");
       System.out.println(item.getItemId()+" ,"+item.getListPrice()+","+item.getAttribute1());
    }
    @Test
    void testAccount(){
        Account account=accountService.getAccount("j2ee");
        System.out.println(account.getAddress1()+","+account.getEmail()+"1");

        Account account1=accountService.getAccount("j2ee","j2ee");
        System.out.println(account1.getEmail()+","+account.getUsername()+"2");
//
//        Account testaccount = account;
//        testaccount.setUsername("wsx");
//        testaccount.setPassword("123");
//        accountService.insertAccount(testaccount);
//
//        testaccount.setPassword("123");
//        testaccount.setEmail("wsx@gmail.com");
//        accountService.updateAccount(testaccount);
//
//        System.out.println(testaccount.getEmail()+","+testaccount.getUsername()+testaccount.getPassword()+",3");
    }

    @Test
    void testLineItem(){
    List<LineItem> lineItemList=orderService.getLineItemsByOrderId(1000);
    System.out.println(lineItemList.size());

   LineItem testlineitem=new LineItem();
   testlineitem.setOrderId(1008);
   testlineitem.setLineNumber(1);
   testlineitem.setItemId("EST-12");
   testlineitem.setQuantity(5);
   BigDecimal a =new BigDecimal(20);
   testlineitem.setUnitPrice(a);

   orderService.insertLineItem(testlineitem);

    }

//    @Test
//    void testCart(){
//      CartItem cartItem=cartService.getCartByUsername("j2ee");
//      System.out.println(cartItem.getItem());
////        Account account=accountService.getAccount("j2ee");
////        CartItem cartItem=new CartItem();
////        Item item=catalogService.getItem("EST-14");
////        cartItem.setItem(item);
////        cartItem.setQuantity(5);
////        cartItem.setInStock(false);
////        cartService.insertCartItem(cartItem,account);
//
//
//    }

}
