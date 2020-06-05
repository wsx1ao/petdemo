package org.csu.mypetstore.controller;


import org.csu.mypetstore.domain.Account;
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
            account=null;
            return "manager/edit_account";
        } else if (!account.getPassword().equals(repeatedPassword)) {
            String msg = "两次密码不一致";
            model.addAttribute("msg", msg);
            account=null;
            return "manager/edit_account";
        } else {
            accountService.updateAccount(account);
            return "manager/success";
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
}
