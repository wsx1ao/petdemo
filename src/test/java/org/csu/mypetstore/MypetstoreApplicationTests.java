package org.csu.mypetstore;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("org.csu.mypetstore.persistence")
class MypetstoreApplicationTests {
    @Autowired
    CatalogService catalogService;
    @Autowired
    AccountService accountService;

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

        Account testaccount = account;
        testaccount.setUsername("wsx");
        testaccount.setPassword("123");
        accountService.insertAccount(testaccount);

        testaccount.setPassword("123");
        testaccount.setEmail("wsx@gmail.com");
        accountService.updateAccount(testaccount);

        System.out.println(testaccount.getEmail()+","+testaccount.getUsername()+testaccount.getPassword()+",3");
    }
}
