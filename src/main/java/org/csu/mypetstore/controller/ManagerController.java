package org.csu.mypetstore.controller;


import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("manager")
public class ManagerController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private Order order;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private AccountService accountService;
    private static final List<String> LANGUAGE_LIST;
    private static final List<String> CATEGORY_LIST;

    static {
        List<String> langList = new ArrayList<String>();
        langList.add("ENGLISH");
        langList.add("CHINESE");
        LANGUAGE_LIST = Collections.unmodifiableList(langList);

        List<String> catList = new ArrayList<String>();
        catList.add("FISH");
        catList.add("DOGS");
        catList.add("REPTILES");
        catList.add("CATS");
        catList.add("BIRDS");

        CATEGORY_LIST = Collections.unmodifiableList(catList);
    }

    @GetMapping("login")
    public String view() {
        return "manager/signon";
    }

    @PostMapping("signon")
    public String signon(String username, String password, Model model) {

        if (username.equals("wsx") && password.equals("123")) {
            return "manager/main";
        } else {
            String msg = "Invalid username or password.  Signon failed.";
            model.addAttribute("msg", msg);
            return "manager/signon";
        }
    }

    @GetMapping("ordermanager")
    public String listOrders(Model model) {
        List<Order> orderList = orderService.getallOrders();
        model.addAttribute("orderList", orderList);
        return "manager/listOrders";
    }

    @GetMapping("accountmanager")
    public String listAccounts(Model model){
        List<Account> accountList=accountService.getallAccount();
        model.addAttribute("accountList",accountList);
        return "manager/listaccounts";
    }

    @PostMapping("editAccount")
    public String editAccount(Account account, String repeatedPassword, Model model) {
        if (account.getPassword() == null || account.getPassword().length() == 0 || repeatedPassword == null || repeatedPassword.length() == 0) {
            String msg = "密码不能为空";
            model.addAttribute("msg", msg);
            return "manager/edit_account";
        } else if (!account.getPassword().equals(repeatedPassword)) {
            String msg = "两次密码不一致";
            model.addAttribute("msg", msg);
            return "manager/edit_account";
        } else {
            System.out.print(account.getUsername());
            accountService.updateAccount(account);
            return "manager/main";
        }
    }

    @GetMapping("modifyorder")
    public String viewOrder(int orderId, Model model) {
        order = orderService.getOrder(orderId);
        model.addAttribute("order", order);
        return "manager/modifyorder";
    }
    @GetMapping("modifyaccount")
    public String viewAccount(String accountusername,Model model){
        Account account=accountService.getAccount(accountusername);
        model.addAttribute("account", account);
        model.addAttribute("LANGUAGE_LIST", LANGUAGE_LIST);
        model.addAttribute("CATEGORY_LIST", CATEGORY_LIST);
        return "manager/edit_account";
    }
    @GetMapping("deleteorder")
    public String deletOrder(int orderId) {
        orderService.deleteOrder(orderId);
        return "manager/success";
    }

    @GetMapping("updateorder")
    public String updateOrder(int orderId,String orderStatus){
        if(orderStatus.equals("P")){
            orderService.updateStatusByOrderid(orderId,"UP");
        }
        else orderService.updateStatusByOrderid(orderId,"P");
        return "manager/success";
    }
    @PostMapping("suremodifyorder")
    public String editAccount(Order order, Model model) {
        orderService.updateorder(order);
        return "manager/listOrders";
    }
    @GetMapping("itemmanager")
    public String itemmanager(Model model) {
        List<Item> itemList = catalogService.getAllItem();
        model.addAttribute("itemList",itemList);
        return "manager/itemlist";
    }

    @PostMapping("searchItems")
    public String searchItems(String keyword, Model model, String itemId){
        if(keyword == null || keyword.length() < 1){
            String msg = "Please enter a keyword to search for, then press the search button.";
            model.addAttribute("msg",msg);
            return "manager/error";
        }else {
            Item item = catalogService.getItem(keyword);
            if(item == null){
                String msg = "This itemId don't exist";
                model.addAttribute("msg",msg);
                return "manager/error";
            }else{
                model.addAttribute("item",item);
                return "manager/items";
            }
        }
    }

    @GetMapping("ViewItems")
    public String ViewItems(Model model, String itemId){
        Item item = catalogService.getItem(itemId);
        model.addAttribute("item", item);
        return "manager/items";
    }

    @GetMapping("delete")
    public String delete(Model model, String itemId) {
        catalogService.DeleteItemById1(itemId);
        catalogService.DeleteItemById2(itemId);
        List<Item> itemList = catalogService.getAllItem();
        model.addAttribute("itemList",itemList);
        return "manager/itemlist";
    }

    @GetMapping("newItemsForm")
    public String newItemsForm(Model model) {
        model.addAttribute("newItemsForm",new Item());
        return "manager/new_item";
    }

    @PostMapping("newItem")
    public String newItem(Item item, Model model, int quantity) {
        if(item.getItemId() == null){
            String msg = "itemid不能为空";
            model.addAttribute("msg", msg);
            return "manager/new_item";
        }else if(catalogService.getItem(item.getItemId()) != null){
            String msg = "itemid已存在";
            model.addAttribute("msg", msg);
            return "manager/new_item";
        }else if(catalogService.getProduct(item.getProductId()) == null){
            String msg = "productid不存在";
            model.addAttribute("msg", msg);
            return "manager/new_item";
        } else{
            catalogService.insertItem1(item);
            catalogService.insertItem2(item.getItemId(), quantity);
            List<Item> itemList = catalogService.getAllItem();
            model.addAttribute("itemList",itemList);
            return "manager/itemlist";
        }
    }

    @GetMapping("modifyForm")
    public String modifyForm(Model model,String itemId){
        Item item = catalogService.getItem(itemId);
        model.addAttribute("item",item);
        int quantity = item.getQuantity();
        model.addAttribute("quantity", quantity);
        return "manager/modifyitem";
    }

    @PostMapping("modify")
    public String modify(Item item, Model model, int quantity){
           System.out.print(item.getQuantity());
        Product product=catalogService.getProduct(item.getProductId());
        if(product == null){
            String msg = "productid不存在";
            model.addAttribute("msg", msg);
            return "manager/modifyitem";
        }else{
            catalogService.updateItem1(item);
            catalogService.updateItem2(item.getItemId(),quantity);
            List<Item> itemList = catalogService.getAllItem();
            model.addAttribute("itemList",itemList);
            return "manager/itemlist";
        }
    }
}
